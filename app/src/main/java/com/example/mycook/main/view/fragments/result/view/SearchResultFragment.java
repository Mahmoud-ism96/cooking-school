package com.example.mycook.main.view.fragments.result.view;

import static com.example.mycook.ResultType.REMOTE_RESULT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;
import com.example.mycook.SearchType;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.result.presenter.SearchResultPresenter;
import com.example.mycook.main.view.fragments.result.presenter.SearchResultPresenterInterface;
import com.example.mycook.main.view.fragments.result.view.SearchResultFragmentDirections.ActionSearchResultFragmentToMealDetailsFragment;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends Fragment implements SearchResultsInterface, OnSearchResultClickListener {

    SearchResultPresenterInterface searchResultPresenterInterface;
    RecyclerView recyclerView;
    SearchResultAdapter resultAdapter;
    List<Meal> searchResult;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        searchResultPresenterInterface = new SearchResultPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(), ConcreteLocalSource.getInstance(getContext())));

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
    }

    @Override
    public void onItemClickListener(Meal meal) {
        ActionSearchResultFragmentToMealDetailsFragment navigationAction = SearchResultFragmentDirections.actionSearchResultFragmentToMealDetailsFragment(meal, REMOTE_RESULT);
        Navigation.findNavController(getView()).navigate(navigationAction);
    }
}