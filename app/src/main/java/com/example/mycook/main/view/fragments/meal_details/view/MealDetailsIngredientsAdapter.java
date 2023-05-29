package com.example.mycook.main.view.fragments.meal_details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycook.R;
import com.example.mycook.model.Ingredient;

import java.util.List;

public class MealDetailsIngredientsAdapter extends RecyclerView.Adapter<MealDetailsIngredientsAdapter.ViewHolder> {

    private static final String TAG = "MealIngredientsAdapter";
    private Context context;
    private List<Ingredient> ingredients;

    public MealDetailsIngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_measurement;
        public TextView tv_ingredient;
        public ImageView iv_Thumbnail;
        public ConstraintLayout c_layout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            c_layout = itemView.findViewById(R.id.c_meal_ingredient_layout);
            tv_measurement = itemView.findViewById(R.id.tv_meal_measurement);
            tv_ingredient = itemView.findViewById(R.id.tv_meal_ingredient);
            iv_Thumbnail = itemView.findViewById(R.id.iv_meal_ingredient_thumbnail);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.ingredients_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingredientName = ingredients.get(position).getIngredient();
        String imageURL = "https://www.themealdb.com/images/ingredients/" + ingredientName + "-Small.png";
        holder.tv_measurement.setText(ingredients.get(position).getMeasurement());
        holder.tv_ingredient.setText(ingredientName);
        Glide.with(context).load(imageURL)
                .apply(new RequestOptions().override(1000, 1000))
                .placeholder(R.drawable.loading_thumbnail)
                .error(R.drawable.error_thumbnail).into(holder.iv_Thumbnail);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (ingredients != null)
            size = ingredients.size();
        return size;
    }

    public void updateList(List<Ingredient> list) {
        ingredients = list;
    }
}
