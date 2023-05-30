package com.example.mycook.main.view.fragments.home.view;

import static com.example.mycook.util.ResultType.LOCAL_RESULT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.home.presenter.HomePresenter;
import com.example.mycook.main.view.fragments.home.presenter.HomePresenterInterface;
import com.example.mycook.main.view.fragments.home.view.HomeFragmentDirections.ActionNavigationHomeToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.SignUpDialog;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnDailyMealClickListener, HomeInterface {

    HomePresenterInterface homePresenterInterface;
    RecyclerView recyclerView;
    DailyInspirationAdapter dailyInspirationAdapter;
    List<Meal> dailyMeals;
    Group home_group;
    LottieAnimationView loading;

    String TAG = "HOME_FRAGMENT";



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupLoading(view);

        recyclerView = view.findViewById(R.id.rv_daily_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new CarouselLayoutManager());

        dailyInspirationAdapter = new DailyInspirationAdapter(getActivity(), dailyMeals, this);
        recyclerView.setAdapter(dailyInspirationAdapter);

        homePresenterInterface = new HomePresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));
        dailyMeals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            homePresenterInterface.getDailyInspiration();
        }
    }

    @Override
    public void onFavClick(Meal meal) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            addMeal(meal);
        else
            SignUpDialog.showSignupDialog(getActivity());
    }

    @Override
    public void onMealClick(Meal meal) {
        ActionNavigationHomeToMealDetailsFragment navigationAction = HomeFragmentDirections.actionNavigationHomeToMealDetailsFragment(meal, LOCAL_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    @Override
    public void showDailyInspiration(List<Meal> meal) {
        if (!dailyMeals.contains(meal.get(0)))
            dailyMeals.add(meal.get(0));
        dailyInspirationAdapter.updateList(dailyMeals);
        dailyInspirationAdapter.notifyDataSetChanged();
        updateVisibility(View.VISIBLE, View.INVISIBLE);
    }

    @Override
    public void addMeal(Meal meal) {
        homePresenterInterface.addToFav(meal);
    }

    @Override
    public boolean mealExist(String mealID) {
        return homePresenterInterface.mealExist(mealID);
    }

    private void setupLoading(@NonNull View view) {
        home_group = view.findViewById(R.id.home_group);
        loading = view.findViewById(R.id.home_loading);

        updateVisibility(View.INVISIBLE, View.VISIBLE);
    }

    private void updateVisibility(int visible, int invisible) {
        home_group.setVisibility(visible);
        loading.setVisibility(invisible);
    }



}