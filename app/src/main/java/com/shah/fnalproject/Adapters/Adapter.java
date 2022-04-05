package com.shah.fnalproject.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.shah.fnalproject.Database.AppDatabase;
import com.shah.fnalproject.UI.Details;
import com.shah.fnalproject.R;
import com.shah.fnalproject.Database.Recipe;
import com.shah.fnalproject.Database.RecipeDao;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder>{
    List<Recipe> allData;
    Context context;
    AppDatabase db;

    public Adapter(List<Recipe> allData, Context context) {
        this.allData = allData;
        this.context = context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Recipe model_dataList = allData.get(position);



        holder.tvName.setText(model_dataList.name);
        holder.tvType.setText(model_dataList.type);
        holder.details = model_dataList.details;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra("name",model_dataList.name);
                intent.putExtra("type",model_dataList.type);
                intent.putExtra("details",model_dataList.details);
                intent.putExtra("check","1");
                context.startActivity(intent);


            }
        });


        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(holder,model_dataList.rid,position);
            }
        });
    }

    private void delete(myViewHolder holder, int rid, int position) {
        db = Room.databaseBuilder(holder.tvName.getContext(),AppDatabase.class, "recipe_db").allowMainThreadQueries().build();
        RecipeDao recipeDao = db.recipeDao();

        new MaterialAlertDialogBuilder(context)
                .setTitle("Delete")
                .setMessage("Are you sure to delete?")
                .setNeutralButton("Cancel", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //del record from room db
                        recipeDao.deleteById(rid);
                        //del record from arraylist
                        allData.remove(position);
                        notifyDataSetChanged();

                        Toast.makeText(context, "Deleted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvType;
        String details;
        ImageView btnDel;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            btnDel = itemView.findViewById(R.id.btnDelete);
        }
    }


}
