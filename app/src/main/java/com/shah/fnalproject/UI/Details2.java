package com.shah.fnalproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shah.fnalproject.Database.AppDatabase;
import com.shah.fnalproject.Database.AppDatabse2;
import com.shah.fnalproject.Database.Recipe;
import com.shah.fnalproject.Database.Recipe2;
import com.shah.fnalproject.Database.RecipeDao;
import com.shah.fnalproject.Database.RecipeDao2;
import com.shah.fnalproject.Interfaces.FoodApi;
import com.shah.fnalproject.Model.Detail;
import com.shah.fnalproject.Model.Result;
import com.shah.fnalproject.Model.Result2;
import com.shah.fnalproject.R;
import com.shah.fnalproject.RequestManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.*;
public class Details2 extends AppCompatActivity {
    TextView title,ing,link;
    ImageView img,btnBack;
    Button btnFvrtSave;
    private FoodApi foodApi;
    Dialog dialog;
    AppDatabse2 db;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        img = findViewById(R.id.img);
        title = findViewById(R.id.title);
        ing = findViewById(R.id.ing);
        link = findViewById(R.id.link);
        btnBack = findViewById(R.id.btnBack);
        btnFvrtSave = findViewById(R.id.btnFvrtSave);
        dialog = new Dialog(this);
        //creating db instance
        db = Room.databaseBuilder(this, AppDatabse2.class, "recipe_db_2").allowMainThreadQueries().build();

        showLoading();

        foodApi = RequestManager.getFoodApi();

        id = getIntent().getStringExtra("id");
        String check = getIntent().getStringExtra("check");

        if(check.equals("2")){
            btnFvrtSave.setVisibility(View.GONE);
        }


        try {
            int id2 = Integer.parseInt(id);
            getDetails(id2);
        }
        catch (Exception e) {
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnFvrtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });



    }

    private void  getDetails(int id) {
        Call<Result2> call = foodApi.detailsList(id);

        call.enqueue(new Callback<Result2>() {
            @Override
            public void onResponse(Call<Result2> call, Response<Result2> response) {
                Glide.with(img.getContext()).load(response.body().getResultDetail().get(0).getImage().toString())
                        .into(img);
                title.setText(response.body().getResultDetail().get(0).getTitle().toString());
                ing.setText(response.body().getResultDetail().get(0).getIng1()+"\n"
                        +response.body().getResultDetail().get(0).getIng2()+"\n"
                        +response.body().getResultDetail().get(0).getIng3()+"\n"
                        +response.body().getResultDetail().get(0).getIng4()+
                        "\n"+response.body().getResultDetail().get(0).getIng5()+
                        "\n"+response.body().getResultDetail().get(0).getIng6()+
                        "\n"+response.body().getResultDetail().get(0).getIng7()+
                        "\n"+response.body().getResultDetail().get(0).getIng8()+
                        "\n"+response.body().getResultDetail().get(0).getIng9()+
                        "\n"+response.body().getResultDetail().get(0).getIng10()+
                        "\n"+response.body().getResultDetail().get(0).getIng11()+
                        "\n"+response.body().getResultDetail().get(0).getIng12()+
                        "\n"+response.body().getResultDetail().get(0).getIng13()+
                        "\n"+response.body().getResultDetail().get(0).getIng14()+
                        "\n"+response.body().getResultDetail().get(0).getIng15()+
                        "\n"+response.body().getResultDetail().get(0).getIng16()+
                        "\n"+response.body().getResultDetail().get(0).getIng17()+
                        "\n"+response.body().getResultDetail().get(0).getIng18()+"\n"
                        +response.body().getResultDetail().get(0).getIng19()+"\n"
                        +response.body().getResultDetail().get(0).getIng20());

                link.setText(response.body().getResultDetail().get(0).getLink());

            }

            @Override
            public void onFailure(Call<Result2> call, Throwable t) {

            }
        });


    }


    private void addData() {
        String titlee;
        int idd=0;

        try {
            idd= Integer.parseInt(id);
        }catch (Exception e){}

        titlee = title.getText().toString();

        if(TextUtils.isEmpty(titlee)){
            Toast.makeText(Details2.this, "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
        }else {
            Recipe2 recipe = new Recipe2(idd,titlee);
            RecipeDao2 recipeDao = db.recipeDao2();
            recipeDao.insertAll2(recipe);
            Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();

        }


    }


    public void showLoading(){
        final LoadingDailog loadingDailog = new LoadingDailog(Details2.this);
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}