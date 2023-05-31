package com.example.mycook.main.view.fragments.favourites.view;

import static com.example.mycook.util.ResultType.LOCAL_RESULT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.favourites.presenter.FavouritesPresenter;
import com.example.mycook.main.view.fragments.favourites.presenter.FavouritesPresenterInterface;
import com.example.mycook.main.view.fragments.favourites.view.FavouritesFragmentDirections.ActionNavigationFavouritesToMealDetailsFragment;
import com.example.mycook.main.view.fragments.home.view.OnDailyMealClickListener;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class FavouritesFragment extends Fragment implements OnDailyMealClickListener, FavouritesInterface {

    RecyclerView recyclerView;
    FavouritesAdapter favouritesAdapter;
    List<Meal> meal;
    FavouritesPresenterInterface favouritesPresenterInterface;
    TextView tv_favourites_signup;
    Group favourites_group;
    Group favourites_no_data_group;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
            ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(ingredientsLayoutManager);

            favouritesAdapter = new FavouritesAdapter(getActivity(), meal, this);
            recyclerView.setAdapter(favouritesAdapter);

            favouritesPresenterInterface = new FavouritesPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));
            favouritesPresenterInterface.getFavMeals();
        } else {
            showTextSignUp();
        }
    }

    private void initViews(@NonNull View view) {
        tv_favourites_signup = view.findViewById(R.id.tv_favourites_signup);
        favourites_group = view.findViewById(R.id.favourites_group);
        favourites_no_data_group = view.findViewById(R.id.favourites_no_data_group);
        recyclerView = view.findViewById(R.id.rv_favourite_list);
    }

    private void showTextSignUp() {
        tv_favourites_signup.setVisibility(View.VISIBLE);
        favourites_group.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showFavMeals(LiveData<List<Meal>> meals) {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if (!meals.isEmpty()) {
                    favourites_no_data_group.setVisibility(View.INVISIBLE);
                    favourites_group.setVisibility(View.VISIBLE);
                    favouritesAdapter.updateList(meals);
                    favouritesAdapter.notifyDataSetChanged();
                } else {
                    showTextNoData();
                }
            }
        });
    }
    private void showTextNoData() {
        favourites_no_data_group.setVisibility(View.VISIBLE);
        favourites_group.setVisibility(View.INVISIBLE);
    }

    @Override
    public void deleteMeal(Meal meal) {
        favouritesPresenterInterface.removeFromFav(meal);
    }

    @Override
    public void onFavClick(Meal meal) {
        deleteMeal(meal);
    }

    @Override
    public void onMealClick(Meal meal) {
        ActionNavigationFavouritesToMealDetailsFragment navigationAction = FavouritesFragmentDirections.actionNavigationFavouritesToMealDetailsFragment(meal, LOCAL_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    @Override
    public boolean mealExist(String mealID) {
        return favouritesPresenterInterface.mealExist(mealID);
    }
}