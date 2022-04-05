package com.shah.fnalproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shah.fnalproject.R;

public class Details extends AppCompatActivity {
    TextView tvName,tvType,tvDetails;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvName = findViewById(R.id.tvName);
        tvType = findViewById(R.id.tvType);
        tvDetails = findViewById(R.id.tvDetails);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();

        tvName.setText("Recipe Name: "+intent.getStringExtra("name"));
        tvType.setText("Recipe Type: "+intent.getStringExtra("type"));
        tvDetails.setText(intent.getStringExtra("details"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}