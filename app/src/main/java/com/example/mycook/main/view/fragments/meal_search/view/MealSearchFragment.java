package com.example.mycook.main.view.fragments.meal_search.view;

import static com.example.mycook.util.ResultType.LOCAL_RESULT;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.home.view.DailyInspirationAdapter;
import com.example.mycook.main.view.fragments.home.view.OnDailyMealClickListener;
import com.example.mycook.main.view.fragments.meal_search.presenter.MealSearchPresenter;
import com.example.mycook.main.view.fragments.meal_search.presenter.MealSearchPresenterInterface;
import com.example.mycook.main.view.fragments.meal_search.view.MealSearchFragmentDirections.ActionMealSearchFragmentToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MealSearchFragment extends Fragment implements OnDailyMealClickListener, MealSearchInterface {

    TextInputEditText textInputEditText;

    MealSearchPresenterInterface mealSearchPresenterInterface;
    RecyclerView recyclerView;
    DailyInspirationAdapter dailyInspirationAdapter;
    List<Meal> meals;

    public MealSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textInputEditText = view.findViewById(R.id.et_recipe_search);
        textInputEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textInputEditText, InputMethodManager.SHOW_IMPLICIT);

        recyclerView = view.findViewById(R.id.rv_meal_search);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(ingredientsLayoutManager);

        dailyInspirationAdapter = new DailyInspirationAdapter(getActivity(), meals, this);
        recyclerView.setAdapter(dailyInspirationAdapter);

        mealSearchPresenterInterface = new MealSearchPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(), ConcreteLocalSource.getInstance(getContext())));

        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString();
                mealSearchPresenterInterface.getMeals(query);
            }
        });

    }

    @Override
    public void onFavClick(Meal meal) {
        addMeal(meal);
    }

    @Override
    public void onMealClick(Meal meal) {
        ActionMealSearchFragmentToMealDetailsFragment navigationAction = MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(meal, LOCAL_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    @Override
    public boolean mealExist(int mealID) {
        return mealSearchPresenterInterface.mealExist(mealID);
    }

    @Override
    public void showSearchMeals(List<Meal> meal) {
        dailyInspirationAdapter.updateList(meal);
        dailyInspirationAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMeal(Meal meal) {
        mealSearchPresenterInterface.addToFav(meal);
    }
}