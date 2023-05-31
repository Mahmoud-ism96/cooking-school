package com.example.mycook.main.view.fragments.search.view;

import static com.example.mycook.util.SearchType.SEARCH_BY_AREA;
import static com.example.mycook.util.SearchType.SEARCH_BY_CATEGORY;
import static com.example.mycook.util.SearchType.SEARCH_BY_INGREDIENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.MainActivity;
import com.example.mycook.main.view.fragments.ingredients.view.OnIngredientClickListener;
import com.example.mycook.main.view.fragments.search.presenter.SearchPresenter;
import com.example.mycook.main.view.fragments.search.presenter.SearchPresenterInterface;
import com.example.mycook.main.view.fragments.search.view.SearchFragmentDirections.ActionNavigationSearchToSearchResultFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.ConnectionChecker;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;

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
    Group search_group;
    LottieAnimationView loading;
    Group search_group_no_connection;
    String TAG = "SEARCH_FRAGMENT";
    TextInputEditText textInputEditText;
    private PublishSubject<Boolean> stopTrigger = PublishSubject.create();


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
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

        search_group.setVisibility(View.GONE);

        if (ConnectionChecker.checkConnectivity(getActivity())) {
            loading.setVisibility(View.VISIBLE);
            getData();
        } else {
            search_group_no_connection.setVisibility(View.VISIBLE);
            attemptConnect();
        }
    }

    private void initViews(@NonNull View view) {
        rv_ingredients = view.findViewById(R.id.rv_ingredient_list);
        rv_categories = view.findViewById(R.id.rv_category_list);
        rv_area = view.findViewById(R.id.rv_area_list);
        search_group_no_connection = view.findViewById(R.id.group_search_no_connection);
        btn_showAll = view.findViewById(R.id.tv_ingredient_all);
        textInputEditText = view.findViewById(R.id.et_search);
        search_group = view.findViewById(R.id.search_group);
        loading = view.findViewById(R.id.search_loading);
    }

    private void initData() {
        searchPresenterInterface = new SearchPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));

        initIngredients();

        initCategories();

        initAreas();

        btn_showAll.setOnClickListener(view -> onViewAllIngredientsClick());

        textInputEditText.setOnClickListener(view -> Navigation.findNavController(view).navigate(com.example.mycook.main.view.fragments.search.view.SearchFragmentDirections.actionNavigationSearchToMealSearchFragment()));
    }

    private void initIngredients() {
        rv_ingredients.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_ingredients.setLayoutManager(ingredientsLayoutManager);
        ingredientAdapter = new IngredientAdapter(getActivity(), ingredientsList, this);
        rv_ingredients.setAdapter(ingredientAdapter);
    }

    private void initCategories() {
        rv_categories.setHasFixedSize(true);
        LinearLayoutManager categoriesLayoutManager = new LinearLayoutManager(getContext());
        categoriesLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_categories.setLayoutManager(categoriesLayoutManager);
        categoriesAdapter = new CategoriesAdapter(getActivity(), categoriesList, this);
        rv_categories.setAdapter(categoriesAdapter);
    }

    private void initAreas() {
        rv_area.setHasFixedSize(true);
        LinearLayoutManager areaLayoutManager = new LinearLayoutManager(getContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv_area.setLayoutManager(areaLayoutManager);
        areaAdapter = new AreaAdapter(getActivity(), areasList, this);
        rv_area.setAdapter(areaAdapter);
    }

    private void getData() {
        searchPresenterInterface.getIngredients();
        searchPresenterInterface.getCategories();
        searchPresenterInterface.getAreas();
    }

    private void attemptConnect() {
        getData();

        boolean shouldRetry = true;

        if (shouldRetry) {
            Observable.timer(3, TimeUnit.SECONDS).takeUntil(stopTrigger).subscribe(aLong -> attemptConnect());
        }
    }

    private void stopRetry() {
        stopTrigger.onNext(true);
    }

    @Override
    public void showIngredients(List<Meal> meal) {
        stopRetry();
        ingredientAdapter.updateList(meal);
        ingredientAdapter.notifyDataSetChanged();
        search_group.setVisibility(View.VISIBLE);
        search_group_no_connection.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
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
        areaAdapter.updateList(meal);
        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAreaClick(String area) {
        ActionNavigationSearchToSearchResultFragment action = SearchFragmentDirections.actionNavigationSearchToSearchResultFragment(area, SEARCH_BY_AREA);
        Navigation.findNavController(getView()).navigate(action);
    }
}