package com.mohamednader.healthyhabit.Adapters;

import com.mohamednader.healthyhabit.Models.CategoriesModels.Category;

import java.util.List;

public interface OnCategoryClickListener {

    public void onCategoryClick(List<Category> categoryName, int pos);

}
