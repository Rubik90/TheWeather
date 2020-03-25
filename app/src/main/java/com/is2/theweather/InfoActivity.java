package com.is2.theweather;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getIntent();
        TextView aboutText = findViewById(R.id.aboutText);
        aboutText.setVisibility(View.VISIBLE);
        CardView cardView = findViewById(R.id.cardViewInfo);
        cardView.setVisibility(View.VISIBLE);
        aboutText.setText(getResources().getString(R.string.aboutText));

    }

}
