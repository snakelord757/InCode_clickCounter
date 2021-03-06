package com.snakelord.clickcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ClickCounterActivity extends AppCompatActivity {

    private static final String CLICK_COUNTER = "clickCounter";

    public static Intent startCounterActivity(Context context, int counter) {
        Intent clickCounterActivityIntent = new Intent(context, ClickCounterActivity.class);
        clickCounterActivityIntent.putExtra(CLICK_COUNTER, Integer.toString(counter));
        return clickCounterActivityIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_counter);

        TextView counterTextView = findViewById(R.id.counter);
        counterTextView.setText(getIntent().getStringExtra(CLICK_COUNTER));
    }
}
