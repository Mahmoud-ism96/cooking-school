package com.example.mycook.main.view.fragments.meal_details.view;

import static com.example.mycook.util.ResultType.LOCAL_RESULT;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycook.R;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.fragments.meal_details.presenter.MealDetailsPresenter;
import com.example.mycook.main.view.fragments.meal_details.presenter.MealDetailsPresenterInterface;
import com.example.mycook.model.Ingredient;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.ResultType;
import com.example.mycook.util.SignUpDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MealDetailsFragment extends Fragment implements MealDetailsInterface, OnMealDetailsClickListener {

    String TAG = "MealDetailsFragment";
    private ImageView iv_thumbnail;
    private ImageButton btn_fav;
    private ImageButton btn_addToPlan;
    private TextView tv_title;
    private TextView tv_category;
    private TextView tv_area;
    private TextView tv_description;
    private YouTubePlayerView yt_player;
    Meal meal;
    ResultType resultType;
    RecyclerView rv_ingredients;
    MealDetailsIngredientsAdapter mealIngredientsAdapter;
    List<Ingredient> ingredientsList;
    private boolean isAddedToFav = false;
    MealDetailsPresenterInterface mealDetailsPresenterInterface;


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

        meal = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealDetail();
        resultType = MealDetailsFragmentArgs.fromBundle(getArguments()).getResultType();

        initViews(view);
        mealDetailsPresenterInterface = new MealDetailsPresenter(this, Repository.getInstance(getContext(), MealsAPI.getInstance(getActivity()), ConcreteLocalSource.getInstance(getContext())));

        ingredientsList = new ArrayList<>();
        if (resultType == LOCAL_RESULT) setupMeal();
        else {
            mealDetailsPresenterInterface.getMealById(meal.getMealID());
        }

    }

    private void initViews(@NonNull View view) {
        iv_thumbnail = view.findViewById(R.id.iv_meal_thumbnail);
        tv_title = view.findViewById(R.id.tv_meal_title);
        tv_category = view.findViewById(R.id.tv_meal_category);
        tv_area = view.findViewById(R.id.tv_meal_area);
        tv_description = view.findViewById(R.id.tv_meal_description);
        yt_player = view.findViewById(R.id.vw_youtube);
        rv_ingredients = view.findViewById(R.id.rv_meal_ingredients);
        btn_fav = view.findViewById(R.id.btn_meal_details_fav);
        btn_addToPlan = view.findViewById(R.id.btn_meal_details_plan);
    }

    private void setupMeal() {

        assignMealToViews();

        initYoutubePlayer();

        setIngredients();

        initRecyclerView();

        mealExist();
    }

    private void assignMealToViews() {
        Glide.with(getContext()).load(meal.getThumbnail()).apply(new RequestOptions().override(500, 500)).placeholder(R.drawable.loading_thumbnail).error(R.drawable.error_thumbnail).into(iv_thumbnail);
        tv_title.setText(meal.getName());
        tv_category.setText(meal.getCategory());
        tv_area.setText(meal.getArea());
        tv_description.setText(meal.getInstructions());
        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    updateFavMeals();
                } else SignUpDialog.showSignupDialog(getActivity());
            }
        });

        btn_addToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) showPlanDialog(view);
                else SignUpDialog.showSignupDialog(getActivity());
            }
        });
    }

    private void updateFavMeals() {
        mealDetailsPresenterInterface.addToFav(meal);
        if (isAddedToFav) isAddedToFav = false;
        else isAddedToFav = true;
        setFavIcon();
    }

    private void showPlanDialog(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(view.getContext());
        builder.setIcon(R.drawable.edit_calendar_black_24dp);
        builder.setTitle("Add Meal to Weekly Plan");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Saturday");
        arrayAdapter.add("Sunday");
        arrayAdapter.add("Monday");
        arrayAdapter.add("Tuesday");
        arrayAdapter.add("Wednesday");
        arrayAdapter.add("Thursday");
        arrayAdapter.add("Friday");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                meal.setWeekDay(strName);
                Toast.makeText(view.getContext(), "Meal is added to " + strName + "'s Plan", Toast.LENGTH_SHORT).show();
                if (!isAddedToFav) {
                    isAddedToFav = true;
                    mealDetailsPresenterInterface.addToFav(meal);
                } else {
                    mealDetailsPresenterInterface.addToPlan(meal, strName);
                }
                setFavIcon();
            }
        });
        builder.show();
    }

    private void initYoutubePlayer() {
        yt_player.setEnableAutomaticInitialization(true);
        getLifecycle().addObserver(yt_player);

        yt_player.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                String videoId = extractVideoIdFromUrl(meal.getYoutubeURL());
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, 0);
                }
            }
        });
    }

    public void setIngredients() {
        for (int i = 1; i <= 20; i++) {
            String ingredient = null;
            String measurement = null;
            try {
                ingredient = (String) meal.getClass().getMethod("getIngredient" + i).invoke(meal);
                measurement = (String) meal.getClass().getMethod("getMeasurement" + i).invoke(meal);
                if (ingredient == null) ingredient = "";

                if (!ingredient.equals("")) {
                    ingredientsList.add(new Ingredient(ingredient, measurement));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
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

    private void initRecyclerView() {
        rv_ingredients.setHasFixedSize(true);
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(getContext());
        ingredientsLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_ingredients.setLayoutManager(ingredientsLayoutManager);
        rv_ingredients.setNestedScrollingEnabled(false);

        initAdapter();
    }

    private void initAdapter() {
        mealIngredientsAdapter = new MealDetailsIngredientsAdapter(getActivity(), ingredientsList);
        rv_ingredients.setAdapter(mealIngredientsAdapter);
    }

    private void mealExist() {
        isAddedToFav = mealDetailsPresenterInterface.mealExist(meal.getMealID());
        setFavIcon();
    }

    public void setFavIcon() {
        if (isAddedToFav) btn_fav.setImageResource(R.drawable.turned_in_white_24dp);
        else btn_fav.setImageResource(R.drawable.turned_in_not_white_24dp);

    }

    @Override
    public void showRemoteMealDetails(List<Meal> meal) {
        this.meal = meal.get(0);
        setupMeal();
    }

    @Override
    public void addMeal(Meal meal) {
        mealDetailsPresenterInterface.addToFav(meal);
    }

    @Override
    public void onFavClick(Meal meal) {
        addMeal(meal);
    }

    @Override
    public boolean mealExist(String mealID) {
        return mealDetailsPresenterInterface.mealExist(mealID);
    }
}