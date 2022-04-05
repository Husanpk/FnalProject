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
import com.shah.fnalproject.Database.AppDatabse2;
import com.shah.fnalproject.Database.Recipe;
import com.shah.fnalproject.Database.Recipe2;
import com.shah.fnalproject.Database.RecipeDao;
import com.shah.fnalproject.Database.RecipeDao2;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.R;
import com.shah.fnalproject.UI.Details;
import com.shah.fnalproject.UI.Details2;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.myViewHolder>{

    List<Recipe2> allData;
    MainActivity context;
    AppDatabse2 db;

    public Adapter2(List<Recipe2> allData, MainActivity context) {
        this.allData = allData;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new Adapter2.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Recipe2 model_dataList = allData.get(position);



        holder.tvName.setText(model_dataList.title);
        holder.tvType.setVisibility(View.GONE);
        String id = String.valueOf(model_dataList.id);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details2.class);
                intent.putExtra("id",id);
                intent.putExtra("check","2");
                context.startActivity(intent);


            }
        });


        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(holder, String.valueOf(model_dataList.id),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allData.size();
    }



    class myViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvType;
        String id;
        ImageView btnDel;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            btnDel = itemView.findViewById(R.id.btnDelete);
        }
    }

    private void delete(Adapter2.myViewHolder holder, String rid, int position) {
        db = Room.databaseBuilder(holder.tvName.getContext(),AppDatabse2.class, "recipe_db_2").allowMainThreadQueries().build();
        RecipeDao2 recipeDao = db.recipeDao2();

        new MaterialAlertDialogBuilder(context)
                .setTitle("Delete")
                .setMessage("Are you sure to delete?")
                .setNeutralButton("Cancel", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //del record from room db
                        recipeDao.deleteById2(rid);
                        //del record from arraylist
                        allData.remove(position);
                        notifyDataSetChanged();

                        Toast.makeText(context, "Deleted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
