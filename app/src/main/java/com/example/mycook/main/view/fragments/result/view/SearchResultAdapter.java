package com.example.mycook.main.view.fragments.result.view;

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

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

   private static final String TAG = "SearchResultAdapter";
   private Context context;
   private List<Meal> meals;
   private OnSearchResultClickListener listener;

   public SearchResultAdapter(Context context, List<Meal> meals, OnSearchResultClickListener listener) {
      this.context = context;
      this.meals = meals;
      this.listener = listener;
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      public TextView tv_Title;
      public ImageView iv_Thumbnail;
      public ImageButton btn_addToFav;
      public CardView cardView;
      public View layout;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         layout = itemView;
         cardView = itemView.findViewById(R.id.search_result_card_view);
         tv_Title = itemView.findViewById(R.id.search_result_title);
         iv_Thumbnail = itemView.findViewById(R.id.search_result_thumbnail);
         btn_addToFav = itemView.findViewById(R.id.btn_search_result_fav);
      }
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
      View v = inflater.inflate(R.layout.search_result_item, recyclerView, false);
      ViewHolder viewHolder = new ViewHolder(v);
      return viewHolder;
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.tv_Title.setText(meals.get(position).getName());
      Glide.with(context).load(meals.get(position).getThumbnail())
              .apply(new RequestOptions().override(1000, 1000))
              .placeholder(R.drawable.loading_thumbnail)
              .error(R.drawable.error_thumbnail).into(holder.iv_Thumbnail);
      holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            listener.onItemClickListener(meals.get(position));
         }
      });

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
