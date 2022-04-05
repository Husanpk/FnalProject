package com.shah.fnalproject.Interfaces;

import com.shah.fnalproject.Model.Detail;
import com.shah.fnalproject.Model.Food;
import com.shah.fnalproject.Model.Result;
import com.shah.fnalproject.Model.Result2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FoodApi {

    @GET("filter.php?")
    Call<Result> searchList(@Query("i") String ing);

    @GET("filter.php?i=chicken_breast")
    Call<Result> randomList();

    @GET("lookup.php?")
    Call<Result2> detailsList(@Query("i") int id);


}
