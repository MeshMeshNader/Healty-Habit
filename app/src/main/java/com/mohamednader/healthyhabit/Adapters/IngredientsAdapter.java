package com.mohamednader.healthyhabit.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.Models.MealsModels.Ingredient;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

    private Context context;
    private List<Ingredient> ingredient;
    private static final String TAG="Days_Adapter";

    public IngredientsAdapter(Context context, List<Ingredient> ingredient) {
        this.context = context;
        this.ingredient = ingredient;
    }

    public void setList(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }


    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        holder.titleTV.setText(ingredient.get(position).getTitle());
        holder.subTitleTV.setText(ingredient.get(position).getSubtitle());
        Log.i(TAG, "onBindViewHolder: " + ingredient.get(position).getPhoto());

        Glide.with(context)
                .load(ingredient.get(position).getPhoto())
                .error(R.drawable.placeholder)
                .into(holder.photoImg);



    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public TextView subTitleTV;
        public ImageView photoImg;

        public ViewHolder(@NonNull View v) {
            super(v);
            titleTV = v.findViewById(R.id.title_text_view);
            subTitleTV = v.findViewById(R.id.sub_title_text_view);
            photoImg = v.findViewById(R.id.photo_img);
        }
    }

}
