package com.example.mycook.main.view.fragments.search.view;

import static com.example.mycook.util.SearchType.SEARCH_BY_AREA;
import static com.example.mycook.util.SearchType.SEARCH_BY_CATEGORY;
import static com.example.mycook.util.SearchType.SEARCH_BY_INGREDIENT;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.ingredients.view.OnIngredientClickListener;
import com.example.mycook.main.view.fragments.search.presenter.SearchPresenter;
import com.example.mycook.main.view.fragments.search.presenter.SearchPresenterInterface;
import com.example.mycook.main.view.fragments.search.view.SearchFragmentDirections.ActionNavigationSearchToSearchResultFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class SearchFragment extends Fragment implements OnIngredientClickListener, OnSearchItemClickListener, SearchInterface {

    SearchPresenterInterface searchPresenterInterface;
    RecyclerView rv_ingredients;
    RecyclerView rv_categories;
    RecyclerView rv_area;
    IngredientAdapter ingredientAdapter;
    CategoriesAdapter categoriesAdapter;
    AreaAdapter areaAdapter;
    List<Meal> ingredientsList;
    List<Meal> categoriesList;
    List<Meal> areasList;

    TextView btn_showAll;

    String TAG = "SEARCH_FRAGMENT";

    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_ingredients = view.findViewById(R.id.rv_ingredient_list);
        rv_ingredients.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_ingredients.setLayoutManager(ingredientsLayoutManager);

        ingredientAdapter = new IngredientAdapter(getActivity(), ingredientsList, this);
        rv_ingredients.setAdapter(ingredientAdapter);
        searchPresenterInterface = new SearchPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        searchPresenterInterface.getIngredients();

        rv_categories = view.findViewById(R.id.rv_category_list);
        rv_categories.setHasFixedSize(true);
        LinearLayoutManager categoriesLayoutManager = new LinearLayoutManager(getContext());
        categoriesLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_categories.setLayoutManager(categoriesLayoutManager);

        categoriesAdapter = new CategoriesAdapter(getActivity(), categoriesList, this);
        rv_categories.setAdapter(categoriesAdapter);
        searchPresenterInterface.getCategories();

        rv_area = view.findViewById(R.id.rv_area_list);
        rv_area.setHasFixedSize(true);
        LinearLayoutManager areaLayoutManager = new LinearLayoutManager(getContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_area.setLayoutManager(areaLayoutManager);

        areaAdapter = new AreaAdapter(getActivity(), areasList, this);
        rv_area.setAdapter(areaAdapter);
        searchPresenterInterface.getAreas();


        btn_showAll = view.findViewById(R.id.tv_ingredient_all);

        btn_showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewAllIngredientsClick();
            }
        });

        textInputEditText = view.findViewById(R.id.et_search);
        textInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(SearchFragmentDirections.actionNavigationSearchToMealSearchFragment());
            }
        });

    }

    @Override
    public void showIngredients(List<Meal> meal) {
        ingredientAdapter.updateList(meal);
        ingredientAdapter.notifyDataSetChanged();
    }

    @Override
    public void onIngredientClick(String ingredient) {
        ActionNavigationSearchToSearchResultFragment action = SearchFragmentDirections.actionNavigationSearchToSearchResultFragment(ingredient, SEARCH_BY_INGREDIENT);
        Navigation.findNavController(getView()).navigate(action);
    }

    public void onViewAllIngredientsClick() {
        Navigation.findNavController(getView()).navigate(SearchFragmentDirections.actionNavigationSearchToIngredientsFragment());
    }

    @Override
    public void showCategories(List<Meal> meal) {
        categoriesAdapter.updateList(meal);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoriesClick(String category) {
        ActionNavigationSearchToSearchResultFragment action = SearchFragmentDirections.actionNavigationSearchToSearchResultFragment(category, SEARCH_BY_CATEGORY);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void showAreas(List<Meal> meal) {
        Log.i(TAG, meal.get(0).getArea());
        areaAdapter.updateList(meal);
        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAreaClick(String area) {
        ActionNavigationSearchToSearchResultFragment action = SearchFragmentDirections.actionNavigationSearchToSearchResultFragment(area, SEARCH_BY_AREA);
        Navigation.findNavController(getView()).navigate(action);
    }
}