package com.shah.fnalproject.Model;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("idMeal")
    private String id;
    @SerializedName("strMeal")
    private String title;
    @SerializedName("strMealThumb")
    private String image;

    public Food(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //    public Food(Integer id, String title, String image) {
//        this.id = id;
//        this.title = title;
//        this.image = image;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
}