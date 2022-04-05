package com.shah.fnalproject.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.Model.Food;
import com.shah.fnalproject.Adapters.RandomRecipes;
import com.shah.fnalproject.Interfaces.FoodApi;
import com.shah.fnalproject.Model.Result;
import com.shah.fnalproject.R;
import com.shah.fnalproject.RequestManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class activity4 extends Fragment {
    MainActivity mainActivity;
    private RecyclerView recyclerView;
    public RandomRecipes adapter = new RandomRecipes();
    private RecyclerView.LayoutManager layoutManager;
    private List<Food> myFoodList = new ArrayList<>();
    private FoodApi foodApi;


    public activity4() {
        super(R.layout.layout_activity4);
    }

    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rec_view);
        foodApi = RequestManager.getFoodApi();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        showLoading();
        randomList();


    }

    public void loadRecipeData() {




//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                myFoodList = response.body().getResult();
//                adapter.setMyFoodList(myFoodList);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//
//            }
//        });
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
        } ,5000);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_view,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               searchList(newText);
               return true;
            }
        });


    }

    private void  searchList(String ing) {
        //Call<Result> call = foodApi.foodList();
        Call<Result> call2 = foodApi.searchList(ing);

        call2.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getResult()==null || response.body().getResult().isEmpty() ){}
                    //Toast.makeText(mainActivity, "No data", Toast.LENGTH_SHORT).show();
                else {
                    myFoodList = response.body().getResult();
                    adapter.setMyFoodList(myFoodList,mainActivity);
                    recyclerView.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
    private void  randomList(){
            //Call<Result> call = foodApi.foodList();
            Call<Result> call2 = foodApi.randomList();

            call2.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if(response.body().getResult().isEmpty())
                        Toast.makeText(mainActivity, "No data", Toast.LENGTH_SHORT).show();
                    else
                    {
                        myFoodList = response.body().getResult();
                        adapter.setMyFoodList(myFoodList, mainActivity);
                        recyclerView.setAdapter(adapter);
                    }


                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
    }
}