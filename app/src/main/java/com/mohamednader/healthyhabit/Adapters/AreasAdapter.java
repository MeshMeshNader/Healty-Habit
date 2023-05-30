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
import com.mohamednader.healthyhabit.Models.MealsModels.Meal;
import com.mohamednader.healthyhabit.R;
import com.mohamednader.healthyhabit.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AreasAdapter extends RecyclerView.Adapter<AreasAdapter.ViewHolder> {


    private List<Meal> areas = new ArrayList<>();
    private static final String TAG = "Areas_Adapter";
    private OnAreaClickListener onAreaClickListener;
    private Context context;

    public AreasAdapter(Context context, List<Meal> areas, OnAreaClickListener onAreaClickListener) {
        this.areas = areas;
        this.context = context;
        this.onAreaClickListener = onAreaClickListener;
    }

    public void setList(List<Meal> areas) {
        this.areas = areas;
    }

    @NonNull
    @Override
    public AreasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(recyclerView.getContext());
        View view = layoutInflater.inflate(R.layout.item_recycler_area, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AreasAdapter.ViewHolder holder, int position) {
        Meal area = areas.get(position);
        holder.areaNameTxtView.setText(area.getStrArea());
        Glide.with(context)
                .load(Utils.generateFlagUrl(area.getStrArea()))
                .into(holder.areaImg);
        holder.areaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAreaClickListener.onAreaClick(area.getStrArea());
            }
        });
    }


    @Override
    public int getItemCount() {
        return areas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView areaImg;
        TextView areaNameTxtView;
        CardView areaCardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            areaImg = view.findViewById(R.id.area_thumb);
            areaNameTxtView = view.findViewById(R.id.area_name);
            areaCardView = view.findViewById(R.id.area_card_view);
        }
    }


}
