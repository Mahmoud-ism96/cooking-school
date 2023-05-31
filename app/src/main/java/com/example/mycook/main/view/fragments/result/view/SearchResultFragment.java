package com.example.mycook.main.view.fragments.result.view;

import static com.example.mycook.util.ResultType.REMOTE_RESULT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.mycook.main.view.fragments.result.presenter.SearchResultPresenter;
import com.example.mycook.main.view.fragments.result.presenter.SearchResultPresenterInterface;
import com.example.mycook.main.view.fragments.result.view.SearchResultFragmentDirections.ActionSearchResultFragmentToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.SearchType;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment implements SearchResultsInterface, OnSearchResultClickListener {

    SearchResultPresenterInterface searchResultPresenterInterface;
    RecyclerView recyclerView;
    SearchResultAdapter resultAdapter;
    List<Meal> searchResult;
    Group result_group;
    LottieAnimationView loading;

    String TAG = "SearchResultFragment";


    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) requireActivity()).btn_back.setVisibility(View.VISIBLE);
        ((MainActivity) requireActivity()).navView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).btn_back.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) requireActivity()).navView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLoading(view);

        String searchData = SearchResultFragmentArgs.fromBundle(getArguments()).getSearchData();
        SearchType searchType = SearchResultFragmentArgs.fromBundle(getArguments()).getSearchType();

        recyclerView = view.findViewById(R.id.rv_search_result);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(ingredientsLayoutManager);
        searchResult = new ArrayList<>();
        resultAdapter = new SearchResultAdapter(getActivity(), searchResult, this);
        recyclerView.setAdapter(resultAdapter);
        searchResultPresenterInterface = new SearchResultPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));

        switch (searchType) {
            case SEARCH_BY_INGREDIENT: {
                searchResultPresenterInterface.getSearchResultsByIngredients(searchData);
            }
            break;
            case SEARCH_BY_CATEGORY: {
                searchResultPresenterInterface.getSearchResultsByCategory(searchData);
            }
            break;
            case SEARCH_BY_AREA: {
                searchResultPresenterInterface.getSearchResultsByArea(searchData);
            }
            break;
        }


    }

    @Override
    public void showSearchResults(List<Meal> meal) {
        resultAdapter.updateList(meal);
        resultAdapter.notifyDataSetChanged();
        updateVisibility(View.VISIBLE, View.INVISIBLE);
    }

    @Override
    public void onItemClickListener(Meal meal) {
        ActionSearchResultFragmentToMealDetailsFragment navigationAction = SearchResultFragmentDirections.actionSearchResultFragmentToMealDetailsFragment(meal, REMOTE_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }

    private void setupLoading(@NonNull View view) {
        result_group = view.findViewById(R.id.result_group);
        loading = view.findViewById(R.id.result_loading);

        updateVisibility(View.INVISIBLE, View.VISIBLE);
    }

    private void updateVisibility(int visible, int invisible) {
        result_group.setVisibility(visible);
        loading.setVisibility(invisible);
    }
}