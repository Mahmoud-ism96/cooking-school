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
import com.example.mycook.main.view.MainActivity;
import com.example.mycook.main.view.fragments.home.presenter.HomePresenter;
import com.example.mycook.main.view.fragments.home.presenter.HomePresenterInterface;
import com.example.mycook.main.view.fragments.home.view.HomeFragmentDirections.ActionNavigationHomeToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.ConnectionChecker;
import com.example.mycook.util.SignUpDialog;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;


public class HomeFragment extends Fragment implements OnDailyMealClickListener, HomeInterface {

    HomePresenterInterface homePresenterInterface;
    RecyclerView recyclerView;
    DailyInspirationAdapter dailyInspirationAdapter;
    List<Meal> dailyMeals;
    Group home_group_no_connection;
    LottieAnimationView loading;

    private PublishSubject<Boolean> stopTrigger = PublishSubject.create();

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
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).navView.setVisibility(View.VISIBLE);
        ((MainActivity) requireActivity()).btn_back.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        initData();



        if (ConnectionChecker.checkConnectivity(getActivity())) {
            loading.setVisibility(View.VISIBLE);
            getData();
        } else {
            home_group_no_connection.setVisibility(View.VISIBLE);
            attemptConnect();
        }

    }

    private void initViews(@NonNull View view) {
        home_group_no_connection = view.findViewById(R.id.group_home_no_connection);
        loading = view.findViewById(R.id.home_loading);
        recyclerView = view.findViewById(R.id.rv_daily_list);
    }

    private void initData() {
        dailyMeals = new ArrayList<>();
        homePresenterInterface = new HomePresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));
        dailyInspirationAdapter = new DailyInspirationAdapter(getActivity(), dailyMeals, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new CarouselLayoutManager());
        recyclerView.setAdapter(dailyInspirationAdapter);
    }

    private void attemptConnect() {
        getData();

        boolean shouldRetry = true;

        if (shouldRetry) {
            Observable.timer(3, TimeUnit.SECONDS).takeUntil(stopTrigger).subscribe(aLong -> attemptConnect());
        }
    }

    private void getData() {
        for (int i = 0; i < 5; i++) {
            homePresenterInterface.getDailyInspiration();
        }
    }

    private void stopRetry() {
        stopTrigger.onNext(true);
    }

    @Override
    public void onFavClick(Meal meal) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) addMeal(meal);
        else SignUpDialog.showSignupDialog(getActivity());
    }

    @Override
    public void onMealClick(Meal meal) {
        ActionNavigationHomeToMealDetailsFragment navigationAction = HomeFragmentDirections.actionNavigationHomeToMealDetailsFragment(meal, LOCAL_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    @Override
    public void showDailyInspiration(List<Meal> meal) {
        stopRetry();
        if (!dailyMeals.contains(meal.get(0)) && dailyMeals.size() < 5) dailyMeals.add(meal.get(0));
        dailyInspirationAdapter.updateList(dailyMeals);
        dailyInspirationAdapter.notifyDataSetChanged();
        home_group_no_connection.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void addMeal(Meal meal) {
        homePresenterInterface.addToFav(meal);
    }

    @Override
    public boolean mealExist(String mealID) {
        return homePresenterInterface.mealExist(mealID);
    }
}