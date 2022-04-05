package com.shah.fnalproject.Model;

import com.google.gson.annotations.SerializedName;
import com.shah.fnalproject.Model.Food;

import java.util.List;

public class Result {

    @SerializedName("meals")
    private List<Food> result;

    public List<Food> getResult() {
        return  result;
    }







}
