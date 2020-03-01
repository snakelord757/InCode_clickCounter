package com.snakelord.clickcounter;

import android.content.Context;
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

import static com.snakelord.clickcounter.ClickCounterActivity.startCounterActivity;

public class MainActivity extends AppCompatActivity {
    private static final String USER_CLICK_COUNTER = "userClickCounter";
    private static final String NUMBER_OF_CLICK = "numberOfClick";
    private static final String COOKIE_STATUS_NAME = "cookieStatusName";
    private int counter = 0;
    private CookieStates status;
    private String cookieStatusName;
    private SharedPreferences userClickCounterSharedPreferences;
    private Animation crumbsFallingAnimation;
    private Animation cookieZoomAnimation;
    private ImageButton addClickImageButton;
    private ImageView crumbsImageView;
    private TextView clickCounterTextView;

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
        setCrumbsFallingAnimationListener();
        setAddClickImageButtonClickListener();
    }

    private void loadClickCounterAndCookieStatus() {
        counter = userClickCounterSharedPreferences.getInt(NUMBER_OF_CLICK, 0);
        clickCounterTextView.setText(getString(R.string.сlick_counter_text, counter));
        cookieStatusName = userClickCounterSharedPreferences.getString(COOKIE_STATUS_NAME, "STATE_FULL");
        status = CookieStates.valueOf(cookieStatusName);
    }

    private void setCookieStatus() {
        switch (status) {
            case STATE_DAMAGED_10_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_10);
                break;

            case STATE_DAMAGED_20_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_20);
                break;

            case STATE_DAMAGED_30_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_30);
                break;

            case STATE_DAMAGED_40_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_40);
                break;

            case STATE_DAMAGED_50_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_50);
                break;

            case STATE_DAMAGED_60_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_60);
                break;

            case STATE_DAMAGED_70_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_70);
                break;

            case STATE_DAMAGED_80_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_80);
                break;

            case STATE_DAMAGED_90_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_90);
                break;

            case STATE_DAMAGED_100_PERCENTS:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button_100);
                break;

            default:
                addClickImageButton.setImageResource(R.drawable.ic_cookie_button);
        }
    }

    private void updateCookieStatus() {
        if (counter >= CookieDamageStates.DAMAGE_STATE_10_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_20_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_10_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_20_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_30_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_20_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_30_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_40_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_30_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_40_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_50_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_40_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_50_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_60_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_50_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_60_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_70_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_60_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_70_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_80_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_70_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_80_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_90_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_80_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_90_PERCENTS.getValue() && counter < CookieDamageStates.DAMAGE_STATE_100_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_90_PERCENTS;
        else if (counter >= CookieDamageStates.DAMAGE_STATE_100_PERCENTS.getValue())
            status = CookieStates.STATE_DAMAGED_100_PERCENTS;
    }

    private void setCrumbsFallingAnimationListener() {
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
            if (counter >= CookieDamageStates.DAMAGE_STATE_COOKIE_IS_GONE.getValue()) {
                addClickImageButton.setVisibility(View.GONE);
            } else {
                clickCounterTextView.setText(getString(R.string.сlick_counter_text, ++counter));
                updateCookieStatus();
                setCookieStatus();
                v.startAnimation(cookieZoomAnimation);
                crumbsImageView.startAnimation(crumbsFallingAnimation);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.click_counter_activity_button) {
            startActivity(startCounterActivity(this, counter));
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
        cookieStatusName = status.name();
        SharedPreferences.Editor clicksCounterEditor = userClickCounterSharedPreferences.edit();
        clicksCounterEditor.putInt(NUMBER_OF_CLICK, counter);
        clicksCounterEditor.putString(COOKIE_STATUS_NAME, cookieStatusName);
        clicksCounterEditor.apply();
        super.onStop();
    }
}
