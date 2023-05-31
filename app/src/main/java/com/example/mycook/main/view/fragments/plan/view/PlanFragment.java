package com.example.mycook.main.view.fragments.plan.view;

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
import com.example.mycook.main.view.fragments.plan.presenter.PlanPresenter;
import com.example.mycook.main.view.fragments.plan.presenter.PlanPresenterInterface;
import com.example.mycook.main.view.fragments.plan.view.PlanFragmentDirections.ActionNavigationPlanToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment implements OnPlanClickListener, PlanInterface {

    RecyclerView rv_day_list;
    RecyclerView rv_meal_list;
    PlanDaysAdapter planDaysAdapter;
    PlanMealsAdapter planMealsAdapter;
    PlanPresenterInterface planPresenterInterface;
    ArrayList<String> weekDays;
    ArrayList<Meal> meals;
    TextView tv_plan_signup;
    TextView tv_select_a_day;
    Group plan_group;

    Group plan_no_daily_group;


    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            planPresenterInterface = new PlanPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));

            initWeekDaysList();

            setupPlanDays();
            setupPlanMeals();
        } else {
            showSignUpText();
        }
    }

    private void initViews(@NonNull View view) {
        tv_plan_signup = view.findViewById(R.id.tv_plan_signup);
        tv_select_a_day = view.findViewById(R.id.tv_plan_select_day);
        plan_group = view.findViewById(R.id.plan_group);
        plan_no_daily_group = view.findViewById(R.id.group_no_daily_plan);
        rv_day_list = view.findViewById(R.id.rv_week_days_list);
        rv_meal_list = view.findViewById(R.id.rv_plan_meal_list);
    }

    private void initWeekDaysList() {
        weekDays = new ArrayList<>();
        weekDays.add("Saturday");
        weekDays.add("Sunday");
        weekDays.add("Monday");
        weekDays.add("Tuesday");
        weekDays.add("Wednesday");
        weekDays.add("Thursday");
        weekDays.add("Friday");
    }

    private void setupPlanDays() {
        rv_day_list.setHasFixedSize(true);
        LinearLayoutManager dayLayoutManager = new LinearLayoutManager(getContext());
        dayLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_day_list.setLayoutManager(dayLayoutManager);

        planDaysAdapter = new PlanDaysAdapter(rv_day_list, getActivity(), weekDays, this);
        rv_day_list.setAdapter(planDaysAdapter);
    }

    private void setupPlanMeals() {
        rv_meal_list.setHasFixedSize(true);
        LinearLayoutManager mealLayoutManager = new LinearLayoutManager(getContext());
        mealLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_meal_list.setLayoutManager(mealLayoutManager);

        planMealsAdapter = new PlanMealsAdapter(getActivity(), meals, this);
        rv_meal_list.setAdapter(planMealsAdapter);
    }

    @Override
    public void selectDayMeals(String day) {
        tv_select_a_day.setVisibility(View.INVISIBLE);
        planPresenterInterface.getDayMeals(day);
    }

    @Override
    public void onMealClick(Meal meal) {
        ActionNavigationPlanToMealDetailsFragment navigationAction = PlanFragmentDirections.actionNavigationPlanToMealDetailsFragment(meal, LOCAL_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    @Override
    public void showDayMeals(LiveData<List<Meal>> meals) {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if (!meals.isEmpty()) {
                    showMealRecyclerView();
                    planMealsAdapter.updateList(meals);
                    planMealsAdapter.notifyDataSetChanged();
                } else showTextNoData();
            }
        });
    }

    @Override
    public void removeFromPlan(Meal meal) {
        planPresenterInterface.removeFromWeek(meal);
    }

    private void showMealRecyclerView() {
        plan_no_daily_group.setVisibility(View.INVISIBLE);
        rv_meal_list.setVisibility(View.VISIBLE);
    }

    private void showSignUpText() {
        tv_plan_signup.setVisibility(View.VISIBLE);
        plan_group.setVisibility(View.INVISIBLE);
    }

    private void showTextNoData() {
        rv_meal_list.setVisibility(View.INVISIBLE);
        plan_no_daily_group.setVisibility(View.VISIBLE);
    }
}