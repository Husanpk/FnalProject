package com.shah.fnalproject.UI;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.R;


public class acitivty_persnol extends Fragment {
    MainActivity mainActivity;
    TextView name,phone,email;
    Button btnEdit;
    private Dialog dialog;



    public acitivty_persnol() {
       super(R.layout.acitivty_persnol);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mainActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        btnEdit = view.findViewById(R.id.btnEdit);
        dialog = new Dialog(mainActivity);



       loadData();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });


    }

    private void loadData() {
        SharedPreferences sh = mainActivity.getSharedPreferences("User", MODE_PRIVATE);

        name.setText(sh.getString("name",""));
        phone.setText(sh.getString("phone",""));
        email.setText(sh.getString("email",""));
    }

    private void ShowPopup() {
        Button btnClose,btnSave;
        TextInputEditText tvName,tvPhone,tvEmail;

        dialog.setContentView(R.layout.name_pop);
        btnClose = dialog.findViewById(R.id.btnClose);
        btnSave = dialog.findViewById(R.id.btnSave);
        tvName = dialog.findViewById(R.id.tvName);
        tvPhone = dialog.findViewById(R.id.tvPhone);
        tvEmail = dialog.findViewById(R.id.tvEmail);

        tvName.setText(name.getText().toString());
        tvPhone.setText(phone.getText().toString());
        tvEmail.setText(email.getText().toString());

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = mainActivity.getSharedPreferences("User",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", tvName.getText().toString());
                myEdit.putString("phone", tvPhone.getText().toString());
                myEdit.putString("email", tvEmail.getText().toString());
                myEdit.apply();
                loadData();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}