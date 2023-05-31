package com.mohamednader.healthyhabit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.R;

import java.util.List;

public class SwipeMealAdapter extends BaseAdapter {
/*
    private List<Meal> meals = new ArrayList<>();
    private static final String TAG = "Swipe_Adapter";
    private OnMealClickListener onMealClickListener;
    private Context context;

    public SwipeMealAdapter(Context context, List<Meal> meals, OnMealClickListener onMealClickListener) {
        this.meals = meals;
        this.context = context;
        this.onMealClickListener = onMealClickListener;
    }

    public void setList(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public SwipeMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View view = layoutInflater.inflate(R.layout.item_card_meal, recyclerView, false);
        SwipeMealAdapter.ViewHolder viewHolder = new SwipeMealAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeMealAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealNameTxtView.setText(meal.getStrMeal());
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(holder.mealImg);

        holder.mealFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.onFavMealClick(meal);
            }
        });

        holder.mealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.onMealClick(Integer.parseInt(meal.getIdMeal()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mealImg, mealFavImg;
        TextView mealNameTxtView;
        CardView mealCardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            mealImg = view.findViewById(R.id.meal_thumb);
            mealFavImg = view.findViewById(R.id.meal_fav_img);
            mealNameTxtView = view.findViewById(R.id.meal_name);
            mealCardView = view.findViewById(R.id.meal_card_view);
        }

    }*/


    private List<Meal> meals;
    Context context;

    public SwipeMealAdapter(Context context, List<Meal> data) {
        this.meals = data;
        this.context = context;

    }

    public void setList(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public Meal getItem(int position) {
        return meals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_card_meal, parent, false);
        }

        ImageView mealImg, mealFavImg;
        TextView mealNameTxtView;
        CardView mealCardView;

        mealImg = convertView.findViewById(R.id.meal_thumb);
        mealFavImg = convertView.findViewById(R.id.meal_fav_img);
        mealNameTxtView = convertView.findViewById(R.id.meal_name);
        mealCardView = convertView.findViewById(R.id.meal_card_view);

        Meal meal = meals.get(position);

        mealNameTxtView.setText(meal.getStrMeal());
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(mealImg);

        mealFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      onMealClickListener.onFavMealClick(meal);
            }
        });

        mealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        onMealClickListener.onMealClick(Integer.parseInt(meal.getIdMeal()));
            }
        });


        return convertView;
    }


}
