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

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {

    private static final String TAG = "AREA_ADAPTER";
    private Context context;
    private List<Meal> meals;
    private OnSearchItemClickListener listener;

    private List<String> flagUrls;

    public AreaAdapter(Context context, List<Meal> meals, OnSearchItemClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_Title;
        public ConstraintLayout cLayout;
        public View layout;
        public ImageView iv_Thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            cLayout = itemView.findViewById(R.id.c_search_layout);
            tv_Title = itemView.findViewById(R.id.tv_meal_ingredient);
            iv_Thumbnail = itemView.findViewById(R.id.iv_meal_ingredient_thumbnail);

            flagUrls = new ArrayList<>();
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
        holder.tv_Title.setText(meals.get(position).getArea());
        initFlagURL();
        Glide.with(context).load(flagUrls.get(position)).apply(new RequestOptions().override(200, 200)).placeholder(R.drawable.loading_thumbnail).error(R.drawable.error_thumbnail).into(holder.iv_Thumbnail);
        holder.iv_Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAreaClick(meals.get(position).getArea());
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

    private void initFlagURL() {
        flagUrls.add("https://www.worldometers.info/img/flags/us-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/uk-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/ca-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/ch-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/hr-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/nl-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/eg-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/rp-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/fr-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/gr-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/in-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/ei-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/it-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/jm-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/ja-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/ke-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/my-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/mx-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/mo-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/pl-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/po-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/rs-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/sp-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/th-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/ts-flag.gif");
        flagUrls.add("https://www.worldometers.info/img/flags/tu-flag.gif");
        flagUrls.add("https://www.freepnglogos.com/uploads/globe-png/globe-png-transparent-image-2.png");
        flagUrls.add("https://www.worldometers.info/img/flags/vm-flag.gif");
    }
}


