package com.mohamednader.healthyhabit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;
import com.mohamednader.healthyhabit.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private static final String TAG = "Categories_Adapter";
    private Context context;
    private List<Category> categories = new ArrayList<>();
    private OnCategoryClickListener onCategoryClickListener;

    public CategoriesAdapter(Context context, List<Category> categories, OnCategoryClickListener onCategoryClickListener) {
        this.context = context;
        this.categories = categories;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public void setList(List<Category> categories) {
        this.categories = categories;
    }


    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler_category, recyclerView, false);
        CategoriesAdapter.ViewHolder viewHolder = new CategoriesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        int pos = position;
        holder.categoryNameTxtView.setText(category.getStrCategory());
        Glide.with(context)
                .load(category.getStrCategoryThumb())
                .into(holder.categoryImg);

        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoryClickListener.onCategoryClick(categories, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImg;
        TextView categoryNameTxtView;
        CardView categoryCardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            categoryImg = view.findViewById(R.id.category_thumb);
            categoryNameTxtView = view.findViewById(R.id.category_name);
            categoryCardView = view.findViewById(R.id.category_card_view);
        }
    }
}
