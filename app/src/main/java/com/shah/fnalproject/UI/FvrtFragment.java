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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shah.fnalproject.Adapters.Adapter;
import com.shah.fnalproject.Adapters.Adapter2;
import com.shah.fnalproject.Database.AppDatabase;
import com.shah.fnalproject.Database.AppDatabse2;
import com.shah.fnalproject.Database.Recipe;
import com.shah.fnalproject.Database.Recipe2;
import com.shah.fnalproject.Database.RecipeDao;
import com.shah.fnalproject.Database.RecipeDao2;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.R;

import java.util.List;


public class FvrtFragment extends Fragment {
    MainActivity mainActivity;
    AppDatabse2 db;
    private RecyclerView recyclerView;
    private FragmentManager FM;



    public FvrtFragment() {
        super(R.layout.fragment_fvrt);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = Room.databaseBuilder(mainActivity.getApplicationContext(), AppDatabse2.class, "recipe_db_2").allowMainThreadQueries().build();
        recyclerView = view.findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        showLoading();

        getAllData();

    }

    private void getAllData() {

        RecipeDao2 recipeDao = db.recipeDao2();
        List<Recipe2> allData = recipeDao.getAll2();

        if(allData.isEmpty()){
            Toast.makeText(getContext(), "No data is Added yet", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setAdapter(new Adapter2(allData, (MainActivity) getContext()));


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