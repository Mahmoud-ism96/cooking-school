package com.example.mycook.main.view.fragments.meal_details.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycook.R;
import com.example.mycook.model.Ingredient;
import com.example.mycook.model.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MealDetailsFragment extends Fragment {

    String TAG = "MealDetailsFragment";
    private ImageView iv_thumbnail;
    private TextView tv_title;
    private TextView tv_category;
    private TextView tv_area;
    private TextView tv_description;
    private YouTubePlayerView yt_player;
    Meal meal;
    RecyclerView rv_ingredients;
    MealIngredientsAdapter mealIngredientsAdapter;
    List<Ingredient> ingredientsList;


    public MealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_thumbnail = view.findViewById(R.id.iv_meal_thumbnail);
        tv_title = view.findViewById(R.id.tv_meal_title);
        tv_category = view.findViewById(R.id.tv_meal_category);
        tv_area = view.findViewById(R.id.tv_meal_area);
        tv_description = view.findViewById(R.id.tv_meal_description);
        yt_player = view.findViewById(R.id.vw_youtube);

        meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetail();

        ingredientsList = new ArrayList<>();

        Glide.with(getContext()).load(meal.getThumbnail()).apply(new RequestOptions().override(500, 500)).placeholder(R.drawable.loading_thumbnail).error(R.drawable.error_thumbnail).into(iv_thumbnail);
        tv_title.setText(meal.getName());
        tv_category.setText(meal.getCategory());
        tv_area.setText(meal.getArea());
        tv_description.setText(meal.getInstructions());

        yt_player.setEnableAutomaticInitialization(true);
        getLifecycle().addObserver(yt_player);

        yt_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                String videoId = extractVideoIdFromUrl(meal.getYoutubeURL());

                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        setIngredients();

        rv_ingredients = view.findViewById(R.id.rv_meal_ingredients);
        rv_ingredients.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_ingredients.setLayoutManager(ingredientsLayoutManager);
        rv_ingredients.setNestedScrollingEnabled(false);

        mealIngredientsAdapter = new MealIngredientsAdapter(getActivity(), ingredientsList);
        rv_ingredients.setAdapter(mealIngredientsAdapter);

    }

    public void setIngredients() {
        for (int i = 1; i <= 20; i++) {
            String ingredient = null;
            String measurement = null;
            try {
                ingredient = (String) meal.getClass().getMethod("getIngredient" + i).invoke(meal);
                measurement = (String) meal.getClass().getMethod("getMeasurement" + i).invoke(meal);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            assert ingredient != null;
            if (!ingredient.equals("")) {
                ingredientsList.add(new Ingredient(ingredient, measurement));
            }
        }
    }

    public static String extractVideoIdFromUrl(String url) {
        String videoId = null;
        if (url != null && url.trim().length() > 0 && url.contains("v=")) {
            int startIndex = url.indexOf("v=") + 2;
            String queryString = url.substring(startIndex);
            int endIndex = queryString.indexOf("&");
            if (endIndex == -1) {
                videoId = queryString;
            } else {
                videoId = queryString.substring(0, endIndex);
            }
        }
        return videoId;
    }
}