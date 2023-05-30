package com.example.mycook.main.view.fragments.plan.view;

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

public class PlanMealsAdapter extends RecyclerView.Adapter<PlanMealsAdapter.ViewHolder>{

private static final String TAG="DailyInspirationAdapter";
private Context context;
private List<Meal> meals;
private OnPlanClickListener listener;


public PlanMealsAdapter(Context context,List<Meal> meals,OnPlanClickListener listener){
        this.context=context;
        this.meals=meals;
        this.listener=listener;
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_Title;
    public TextView tv_Area;
    public TextView tv_Category;
    public ImageView iv_Thumbnail;
    public ImageButton btn_removeFromPlan;
    public CardView cardView;
    public View layout;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView;
        cardView = itemView.findViewById(R.id.plan_card_view);
        tv_Title = itemView.findViewById(R.id.plan_card_title);
        tv_Area = itemView.findViewById(R.id.plan_card_area);
        tv_Category = itemView.findViewById(R.id.plan_card_category);
        iv_Thumbnail = itemView.findViewById(R.id.plan_card_thumbnail);
        btn_removeFromPlan = itemView.findViewById(R.id.btn_plan_card_remove);
    }

}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.plan_card_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_Title.setText(meals.get(position).getName());
        holder.tv_Area.setText(meals.get(position).getArea());
        holder.tv_Category.setText(meals.get(position).getCategory());
        Glide.with(context).load(meals.get(position).getThumbnail()).apply(new RequestOptions().override(1000, 1000)).placeholder(R.drawable.loading_thumbnail).error(R.drawable.error_thumbnail).into(holder.iv_Thumbnail);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMealClick(meals.get(position));
            }
        });

        holder.btn_removeFromPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.removeFromPlan(meals.get(position));
            }
        });


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
