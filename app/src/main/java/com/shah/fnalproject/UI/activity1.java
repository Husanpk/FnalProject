package com.shah.fnalproject.UI;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.R;

import java.util.Objects;


public class activity1 extends Fragment {
    private CardView listView,addList,liveData,favrtList;
    FloatingActionButton btnHelp;
    MainActivity mainActivity;
    FragmentManager FM;
    private Dialog dialog;



    public activity1() {

        super(R.layout.fragment_activity1);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        addList = view.findViewById(R.id.addList);
        liveData = view.findViewById(R.id.liveData);
        btnHelp = view.findViewById(R.id.btnHelp);
        favrtList = view.findViewById(R.id.favrtList);
        FM = mainActivity.getSupportFragmentManager();
        dialog = new Dialog(mainActivity);


        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity2.class, null).addToBackStack(null)
                        .commit();
                Objects.requireNonNull(mainActivity.getSupportActionBar()).setTitle(R.string.add);
            }
        });

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity3.class, null).addToBackStack(null)
                        .commit();
                Objects.requireNonNull(mainActivity.getSupportActionBar()).setTitle(R.string.ic_list);
            }
        });

        liveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, activity4.class, null).addToBackStack(null)
                        .commit();
                Objects.requireNonNull(mainActivity.getSupportActionBar()).setTitle(R.string.live_data);
            }
        });

        favrtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FM.beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, FvrtFragment.class, null).addToBackStack(null)
                        .commit();
                Objects.requireNonNull(mainActivity.getSupportActionBar()).setTitle(R.string.favroties);
            }
        });


        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });


    }

    private void ShowPopup() {
        Button btnClose;

        dialog.setContentView(R.layout.custompopup);
        btnClose = dialog.findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}