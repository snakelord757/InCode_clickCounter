package com.snakelord.clickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OnlyClickConterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_click_conter);

        TextView counterTextView = findViewById(R.id.counter);
        counterTextView.setText(getIntent().getStringExtra("Counter"));
    }
}
