package com.example.mycook.main.view.fragments.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;
import com.example.mycook.model.Meal;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {

    private static final String TAG = "AREA_ADAPTER";
    private Context context;
    private List<Meal> meals;
    private OnSearchItemClickListener listener;

    public AreaAdapter(Context context, List<Meal> meals, OnSearchItemClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Title;
        public ConstraintLayout cLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            cLayout = itemView.findViewById(R.id.c_area_search_layout);
            tv_Title = itemView.findViewById(R.id.tv_area_search_title);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.area_search_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String areaName = meals.get(position).getArea();
        holder.tv_Title.setText(areaName);
        Log.i(TAG, areaName);
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


