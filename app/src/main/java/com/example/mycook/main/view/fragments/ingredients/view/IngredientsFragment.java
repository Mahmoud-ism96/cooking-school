package com.example.mycook.main.view.fragments.ingredients.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.ingredients.presenter.IngredientsPresenter;
import com.example.mycook.main.view.fragments.ingredients.presenter.IngredientsPresenterInterface;
import com.example.mycook.main.view.fragments.search.view.IngredientAdapter;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;

import java.util.List;

public class IngredientsFragment extends Fragment implements OnIngredientClickListener, IngredientInterface {

    IngredientsPresenterInterface ingredientsPresenterInterface;
    RecyclerView rv_ingredients;
    IngredientAdapter ingredientAdapter;
    List<Meal> ingredientsList;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_ingredients = view.findViewById(R.id.rv_all_ingredients_list);
        rv_ingredients.setHasFixedSize(true);
        GridLayoutManager ingredientsLayoutManager = new GridLayoutManager(getContext(), 4);
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_ingredients.setLayoutManager(ingredientsLayoutManager);

        ingredientAdapter = new IngredientAdapter(getActivity(), ingredientsList, this);
        rv_ingredients.setAdapter(ingredientAdapter);
        ingredientsPresenterInterface = new IngredientsPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        ingredientsPresenterInterface.getIngredients();

    }

    @Override
    public void showIngredients(List<Meal> meal) {
        ingredientAdapter.updateList(meal);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void onIngredientClick(String ingredient) {

    }
}