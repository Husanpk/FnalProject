package com.shah.fnalproject.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result2 {

    @SerializedName("meals")
    private List<Detail> resultDetail;

    public List<Detail> getResultDetail() {
        return  resultDetail;
    }
}
