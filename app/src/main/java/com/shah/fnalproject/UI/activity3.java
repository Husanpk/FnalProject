package com.shah.fnalproject.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.shah.fnalproject.Adapters.Adapter;
import com.shah.fnalproject.Database.AppDatabase;
import com.shah.fnalproject.Database.Recipe;
import com.shah.fnalproject.Database.RecipeDao;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.R;

import java.util.List;


public class activity3 extends Fragment {
    MainActivity mainActivity;
    AppDatabase db;
    private RecyclerView recyclerView;
    private FragmentManager FM;



    public activity3() {
        super(R.layout.layout_activity3);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = Room.databaseBuilder(mainActivity.getApplicationContext(), AppDatabase.class, "recipe_db").allowMainThreadQueries().build();
        recyclerView = view.findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        showLoading();

        getAllData();

    }

    private void getAllData() {
       
        RecipeDao recipeDao = db.recipeDao();
        List<Recipe> allData = recipeDao.getAll();

        if(allData.isEmpty()){
            Toast.makeText(getContext(), "No data is Added yet", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setAdapter(new Adapter(allData,getContext()));


    }

    public void showLoading(){
        final LoadingDailog loadingDailog = new LoadingDailog(getActivity());
        loadingDailog.startLoading();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDailog.closeLoading();
            }
        } ,3000);
    }
    }
