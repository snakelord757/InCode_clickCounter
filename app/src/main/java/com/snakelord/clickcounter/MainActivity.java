package com.snakelord.clickcounter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private int status = CookieConstants.STATE_FULL;
    private SharedPreferences userClickCounterSharedPreferences;
    private Animation crumbsFallingAnimation;
    private Animation cookieZoomAnimation;
    private ImageButton addClickImageButton;
    private ImageView crumbsImageView;
    private TextView clickCounterTextView;
    static final String USER_CLICK_COUNTER = "userClickCounter";
    static final String NUMBER_OF_CLICK = "numberOfClick";
    static final String COOKIE_STATUS = "cookieStatus";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addClickImageButton = findViewById(R.id.addClickButton);
        clickCounterTextView = findViewById(R.id.click_counter);
        crumbsImageView = findViewById(R.id.crumbs);
        cookieZoomAnimation = AnimationUtils.loadAnimation(this, R.anim.button_zoom);
        crumbsFallingAnimation = AnimationUtils.loadAnimation(this, R.anim.cookie_crums_falling);
        userClickCounterSharedPreferences = getSharedPreferences(USER_CLICK_COUNTER, Context.MODE_PRIVATE);
        crumbsFallingAnimationListener();
        setAddClickImageButtonClickListener();
    }

    private void loadClickCounterAndCookieStatus() {
        counter = userClickCounterSharedPreferences.getInt(NUMBER_OF_CLICK, 0);
        clickCounterTextView.setText(getString(R.string.сlick_counter_text, counter));
        status = userClickCounterSharedPreferences.getInt(COOKIE_STATUS, 0);
    }

    private void setCookieStatus() {
        switch (status) {
            case CookieConstants.STATE_DAMAGED_10_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_10); break; }
            case CookieConstants.STATE_DAMAGED_20_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_20); break; }
            case CookieConstants.STATE_DAMAGED_30_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_30); break; }
            case CookieConstants.STATE_DAMAGED_40_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_40); break; }
            case CookieConstants.STATE_DAMAGED_50_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_50); break; }
            case CookieConstants.STATE_DAMAGED_60_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_60); break; }
            case CookieConstants.STATE_DAMAGED_70_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_70); break; }
            case CookieConstants.STATE_DAMAGED_80_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_80); break; }
            case CookieConstants.STATE_DAMAGED_90_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_90); break; }
            case CookieConstants.STATE_DAMAGED_100_PERCENTS: { addClickImageButton.setImageResource(R.drawable.ic_cookie_button_100); break;}
            default : addClickImageButton.setImageResource(R.drawable.ic_cookie_button);
        }
    }

    private void updateCookieStatus() {
        if (counter >= CookieConstants.DAMAGE_STATE_10_PERCENTS && counter < CookieConstants.DAMAGE_STATE_20_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_10_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_20_PERCENTS && counter < CookieConstants.DAMAGE_STATE_30_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_20_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_30_PERCENTS && counter < CookieConstants.DAMAGE_STATE_40_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_30_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_40_PERCENTS && counter < CookieConstants.DAMAGE_STATE_50_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_40_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_50_PERCENTS && counter < CookieConstants.DAMAGE_STATE_60_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_50_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_60_PERCENTS && counter < CookieConstants.DAMAGE_STATE_70_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_60_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_70_PERCENTS && counter < CookieConstants.DAMAGE_STATE_80_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_70_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_80_PERCENTS && counter < CookieConstants.DAMAGE_STATE_90_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_80_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_90_PERCENTS && counter < CookieConstants.DAMAGE_STATE_100_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_90_PERCENTS;
        else if (counter >= CookieConstants.DAMAGE_STATE_100_PERCENTS)
            status = CookieConstants.STATE_DAMAGED_100_PERCENTS;
    }

    private void crumbsFallingAnimationListener() {
        crumbsFallingAnimation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                crumbsImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                crumbsImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void setAddClickImageButtonClickListener() {
        addClickImageButton.setOnClickListener(v -> {
            if (counter >= CookieConstants.DAMAGE_STATE_COOKIE_IS_GONE) {
                addClickImageButton.setVisibility(View.GONE);
            }
            else {
                clickCounterTextView.setText(getString(R.string.сlick_counter_text, ++counter));
                updateCookieStatus();
                setCookieStatus();
                v.startAnimation(cookieZoomAnimation);
                crumbsImageView.startAnimation(crumbsFallingAnimation);
            }
        });
    }

    private Intent sendCounterToActivity()
    {
        Intent clickCounterActivityIntent = new Intent(this, ClickCounterActivity.class);
        clickCounterActivityIntent.putExtra("Counter", Integer.toString(counter));
        return clickCounterActivityIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.click_counter_activity_button) {
            startActivity(sendCounterToActivity());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        loadClickCounterAndCookieStatus();
        setCookieStatus();
        super.onStart();
    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor clicksCounterEditor = userClickCounterSharedPreferences.edit();
        clicksCounterEditor.putInt(NUMBER_OF_CLICK, counter);
        clicksCounterEditor.putInt(COOKIE_STATUS, status).apply();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        userClickCounterSharedPreferences = null;
        addClickImageButton = null;
        crumbsFallingAnimation = null;
        cookieZoomAnimation = null;
        clickCounterTextView = null;
        crumbsImageView = null;
        super.onDestroy();
    }
}
