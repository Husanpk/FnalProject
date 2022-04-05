package com.shah.fnalproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.Model.Food;
import com.shah.fnalproject.R;
import com.shah.fnalproject.UI.Details2;

import java.util.ArrayList;
import java.util.List;

public class RandomRecipes extends RecyclerView.Adapter<RandomRecipes.RecipesViewHolder> {

    private List<Food> mData = new ArrayList<>();
    private MainActivity context;

    public void setMyFoodList(List<Food> myFoodList, MainActivity mainActivity) {
        this.mData = myFoodList;
        context = mainActivity;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_row, parent, false);
        return new RecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        Food current = mData.get(position);
        Glide.with(holder.imageView.getContext()).load(current.getImage())
                .into(holder.imageView);
        holder.nameView.setText(current.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("id",current.getId());
//                System.out.println(current.getId());
//                holder.nameView.setText(current.getId());
                Intent intent = new Intent(context, Details2.class);
                intent.putExtra("id",current.getId());
                intent.putExtra("check","1");
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class RecipesViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView nameView;

    RecipesViewHolder(View view) {
        super(view);
        imageView = view.findViewById(R.id.recipe_row_image_view);
        nameView = view.findViewById(R.id.recipe_row_text_view);
    }
}

}
