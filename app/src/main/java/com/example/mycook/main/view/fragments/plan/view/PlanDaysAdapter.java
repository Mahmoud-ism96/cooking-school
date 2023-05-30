package com.example.mycook.main.view.fragments.plan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycook.R;

import java.util.List;

public class PlanDaysAdapter extends RecyclerView.Adapter<PlanDaysAdapter.ViewHolder> {

    private static final String TAG = "SearchAdapter";
    private Context context;
    private List<String> weekDays;
    private OnPlanClickListener listener;

    private RecyclerView recyclerView;

    private int selectedPosition = -1;

    public PlanDaysAdapter(RecyclerView recyclerView, Context context, List<String> meals, OnPlanClickListener listener) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.weekDays = meals;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            tv_day = itemView.findViewById(R.id.tv_day);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.week_day_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_day.setText(weekDays.get(position));
        holder.tv_day.setBackgroundResource(isSelected(position) ? R.drawable.text_selected_ripple : R.drawable.text_ripple);
        holder.tv_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldSelectedPosition = selectedPosition;
                selectedPosition = position;
                notifyItemChanged(oldSelectedPosition);
                notifyItemChanged(selectedPosition);
                Log.i(TAG,weekDays.get(position));
                listener.selectDayMeals(weekDays.get(position));
            }
        });
    }

    private boolean isSelected(int position) {
        return selectedPosition == position;
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (weekDays != null)
            size = weekDays.size();
        return size;
    }
}
