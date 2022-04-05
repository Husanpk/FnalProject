package com.shah.fnalproject.UI;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.shah.fnalproject.Database.AppDatabase;
import com.shah.fnalproject.Database.Recipe;
import com.shah.fnalproject.Database.RecipeDao;
import com.shah.fnalproject.MainActivity;
import com.shah.fnalproject.R;


public class activity2 extends Fragment {
    MainActivity mainActivity;
    private TextInputEditText tvName,tvType,tvDetails;
    private Button btnSave,btnReset;
    AppDatabase db;

    public activity2() {
        super(R.layout.layout_activity2);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.tvName);
        tvType = view.findViewById(R.id.tvType);
        tvDetails = view.findViewById(R.id.tvDetails);
        btnSave = view.findViewById(R.id.btnAdd);
        btnReset = view.findViewById(R.id.btnReset);

        //creating db instance
        db = Room.databaseBuilder(mainActivity.getApplicationContext(), AppDatabase.class, "recipe_db").allowMainThreadQueries().build();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });

    }

    private void addData() {
        String name,type,details;
        name = tvName.getText().toString();
        type = tvType.getText().toString();
        details = tvDetails.getText().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(type)||TextUtils.isEmpty(details)){
            Toast.makeText(mainActivity, "Please Fill All the Fields", Toast.LENGTH_SHORT).show();
        }else {
            Recipe recipe = new Recipe(name,type,details);
            RecipeDao recipeDao = db.recipeDao();
            recipeDao.insertAll(recipe);
            Toast.makeText(mainActivity, "Data Added", Toast.LENGTH_SHORT).show();
            resetFields();
        }


    }

    private void resetFields(){
        tvName.setText("");
        tvType.setText("");
        tvDetails.setText("");
    }
}