package com.example.mycook.main.view.fragments.search.view;

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
import com.example.mycook.model.Meal;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private static final String TAG = "SearchAdapter";
    private Context context;
    private List<Meal> meals;
    private OnSearchItemClickListener listener;

    public CategoriesAdapter(Context context, List<Meal> meals, OnSearchItemClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Title;
        public ImageView iv_Thumbnail;
        public ConstraintLayout cLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            cLayout = itemView.findViewById(R.id.c_search_layout);
            tv_Title = itemView.findViewById(R.id.tv_search_title);
            iv_Thumbnail = itemView.findViewById(R.id.iv_search_thumbnail);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.search_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String categoriesName = meals.get(position).getCategory();
        String imageURL = "https://www.themealdb.com/images/category/" + categoriesName + ".png";
        holder.tv_Title.setText(categoriesName);
        Glide.with(context).load(imageURL).apply(new RequestOptions().override(200, 200)).placeholder(R.drawable.loading_thumbnail).error(R.drawable.error_thumbnail).into(holder.iv_Thumbnail);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (meals != null) size = meals.size();
        return size;
    }

    public void updateList(List<Meal> list) {
        meals = list;
    }
}

