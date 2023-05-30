package com.example.mycook.main.view.fragments.ingredients.view;

import static com.example.mycook.util.SearchType.SEARCH_BY_INGREDIENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.ingredients.presenter.IngredientsPresenter;
import com.example.mycook.main.view.fragments.ingredients.presenter.IngredientsPresenterInterface;
import com.example.mycook.main.view.fragments.ingredients.view.IngredientsFragmentDirections.ActionIngredientsFragmentToSearchResultFragment;
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
    Group ingredients_group;
    LottieAnimationView loading;

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

        setupLoading(view);

        rv_ingredients = view.findViewById(R.id.rv_all_ingredients_list);
        rv_ingredients.setHasFixedSize(true);
        GridLayoutManager ingredientsLayoutManager = new GridLayoutManager(getContext(), 4);
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_ingredients.setLayoutManager(ingredientsLayoutManager);

        ingredientAdapter = new IngredientAdapter(getActivity(), ingredientsList, this);
        rv_ingredients.setAdapter(ingredientAdapter);
        ingredientsPresenterInterface = new IngredientsPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));
        ingredientsPresenterInterface.getIngredients();

    }

    @Override
    public void showIngredients(List<Meal> meal) {
        ingredientAdapter.updateList(meal);
        ingredientAdapter.notifyDataSetChanged();
        updateVisibility(View.VISIBLE, View.INVISIBLE);
    }

    @Override
    public void onIngredientClick(String ingredient) {
        ActionIngredientsFragmentToSearchResultFragment navigationAction = IngredientsFragmentDirections.actionIngredientsFragmentToSearchResultFragment(ingredient, SEARCH_BY_INGREDIENT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    private void setupLoading(@NonNull View view) {
        ingredients_group = view.findViewById(R.id.ingredients_group);
        loading = view.findViewById(R.id.ingredients_loading);

        updateVisibility(View.INVISIBLE, View.VISIBLE);
    }

    private void updateVisibility(int visible, int invisible) {
        ingredients_group.setVisibility(visible);
        loading.setVisibility(invisible);
    }
}