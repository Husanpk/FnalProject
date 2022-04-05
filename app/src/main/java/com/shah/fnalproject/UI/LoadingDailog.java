package com.shah.fnalproject.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.shah.fnalproject.R;

public class LoadingDailog {
    Activity activity;
    AlertDialog alertDialog;

    public LoadingDailog(Activity myActivity) {
        activity =myActivity;
    }

  public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(false);
        alertDialog = builder.create();
         alertDialog.show();
    }

    public void closeLoading(){
        alertDialog.dismiss();
    }
}
