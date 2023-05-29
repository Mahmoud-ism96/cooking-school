package com.example.mycook.main.view.fragments.favourites.view;

import static com.example.mycook.util.ResultType.LOCAL_RESULT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.mycook.main.view.fragments.home.view.DailyInspirationAdapter;
import com.example.mycook.main.view.fragments.home.view.OnDailyMealClickListener;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;

import java.util.List;

public class FavouritesFragment extends Fragment implements OnDailyMealClickListener, FavouritesInterface {

    RecyclerView recyclerView;
    DailyInspirationAdapter dailyInspirationAdapter;
    List<Meal> meal;
    FavouritesPresenterInterface favouritesPresenterInterface;

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

        recyclerView = view.findViewById(R.id.rv_favourite_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(ingredientsLayoutManager);

        dailyInspirationAdapter = new DailyInspirationAdapter(getActivity(), meal, this);
        recyclerView.setAdapter(dailyInspirationAdapter);

        favouritesPresenterInterface = new FavouritesPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        favouritesPresenterInterface.getFavMeals();
    }


    @Override
    public void showFavMeals(LiveData<List<Meal>> items) {
        items.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> items) {
                dailyInspirationAdapter.updateList(items);
                dailyInspirationAdapter.notifyDataSetChanged();
            }
        });
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
    public boolean mealExist(int mealID) {
        return favouritesPresenterInterface.mealExist(mealID);
    }
}