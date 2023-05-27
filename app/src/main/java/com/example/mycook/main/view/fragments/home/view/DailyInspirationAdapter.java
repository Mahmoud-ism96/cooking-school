package com.example.mycook.main.view.fragments.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycook.R;
import com.example.mycook.model.Meal;

import java.util.List;

public class DailyInspirationAdapter extends RecyclerView.Adapter<DailyInspirationAdapter.ViewHolder> {

    private static final String TAG = "DailyInspirationAdapter";
    private Context context;
    private List<Meal> meals;
    private OnFavClickListener listener;

    public DailyInspirationAdapter(Context context, List<Meal> meals, OnFavClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Title;
        public TextView tv_Area;
        public TextView tv_Category;
        public ImageView iv_Thumbnail;
        public ImageButton btn_addToFav;
        public CardView cardView;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            cardView = itemView.findViewById(R.id.card_view);
            tv_Title = itemView.findViewById(R.id.card_title);
            tv_Area = itemView.findViewById(R.id.card_area);
            tv_Category = itemView.findViewById(R.id.card_category);
            iv_Thumbnail = itemView.findViewById(R.id.card_thumbnail);
            btn_addToFav = itemView.findViewById(R.id.btn_card_fav);
        }
    }

    @NonNull
    @Override
    public DailyInspirationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.card_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyInspirationAdapter.ViewHolder holder, int position) {
        holder.tv_Title.setText(meals.get(position).getName());
        holder.tv_Area.setText(meals.get(position).getArea());
        holder.tv_Category.setText(meals.get(position).getCategory());
        Glide.with(context).load(meals.get(position).getThumbnail())
                .apply(new RequestOptions().override(500, 500))
                .placeholder(R.drawable.loading_thumbnail)
                .error(R.drawable.error_thumbnail).into(holder.iv_Thumbnail);
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (meals != null)
            size = meals.size();
        return size;
    }

    public void updateList(List<Meal> list) {
        meals = list;
    }
}
