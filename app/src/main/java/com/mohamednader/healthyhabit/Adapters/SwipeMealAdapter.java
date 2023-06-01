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
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.List;

public class SwipeMealAdapter extends BaseAdapter {
    Context context;
    private List<Meal> meals;
    private OnMealClickListener onMealClickListener;

    public SwipeMealAdapter(Context context, List<Meal> data, OnMealClickListener onMealClickListener) {
        this.meals = data;
        this.context = context;
        this.onMealClickListener = onMealClickListener;
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

        if (!(Utils.getSp(context).getBoolean(Utils.IsLoggedOn, false))) {
            mealFavImg.setVisibility(View.GONE);
            mealFavImg.setClickable(false);

        } else {
            mealFavImg.setVisibility(View.VISIBLE);
            mealFavImg.setClickable(true);

            mealFavImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMealClickListener.onFavMealClick(meal);
                }
            });

        }


        mealNameTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.onMealClick(Integer.parseInt(meal.getIdMeal()));
            }
        });


        return convertView;
    }
}
