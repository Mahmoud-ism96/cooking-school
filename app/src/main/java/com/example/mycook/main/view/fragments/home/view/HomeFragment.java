package com.example.mycook.main.view.fragments.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.home.presenter.HomePresenter;
import com.example.mycook.main.view.fragments.home.presenter.HomePresenterInterface;
import com.example.mycook.main.view.fragments.home.view.HomeFragmentDirections.ActionNavigationHomeToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnDailyMealClickListener, HomeInterface {

    HomePresenterInterface homePresenterInterface;
    CarouselRecyclerview recyclerView;
    DailyInspirationAdapter dailyInspirationAdapter;
    List<Meal> dailyMeals;

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

        recyclerView = view.findViewById(R.id.rv_daily_list);
        recyclerView.setHasFixedSize(true);
        dailyInspirationAdapter = new DailyInspirationAdapter(getActivity(), dailyMeals, this);
        recyclerView.setAdapter(dailyInspirationAdapter);
        recyclerView.setIntervalRatio(0.7f);
        homePresenterInterface = new HomePresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        dailyMeals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            homePresenterInterface.getDailyInspiration();
        }
    }

    @Override
    public void onClick(Meal meal) {
        addMeal(meal);
    }

    @Override
    public void onMealClick(Meal meal) {
        ActionNavigationHomeToMealDetailsFragment navigationAction = HomeFragmentDirections.actionNavigationHomeToMealDetailsFragment(meal);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    @Override
    public void showDailyInspiration(List<Meal> meal) {
        dailyMeals.add(meal.get(0));
        dailyInspirationAdapter.updateList(dailyMeals);
        dailyInspirationAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMeal(Meal meal) {
        homePresenterInterface.addToFav(meal);
    }

}