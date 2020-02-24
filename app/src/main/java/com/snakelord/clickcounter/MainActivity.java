package com.snakelord.clickcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class MainActivity extends AppCompatActivity
{

    private int counter = 0;
    private int status = 0;
    private SharedPreferences userClickCounterSharedPreferences;
    private ImageButton addClickImageButtonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userClickCounterSharedPreferences = getSharedPreferences("userClickCounter", Context.MODE_PRIVATE);
        loadClickCounterAndCookieStatus();

        addClickImageButtonButton = findViewById(R.id.addClickButton);
        setCookieStatus();

        final TextView clickCouterTextView = findViewById(R.id.click_counter);
        clickCouterTextView.setText(getString(R.string.сlick_counter_text, counter));

        final ImageView crumbsImageView = findViewById(R.id.crumbs);

        final Animation cookieZoomAnimation = AnimationUtils.loadAnimation(this,R.anim.button_zoom);
        final Animation crumbsFallingAnimation = AnimationUtils.loadAnimation(this,R.anim.cookie_crums_falling);
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

        addClickImageButtonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter >= 1100) {
                    addClickImageButtonButton.setVisibility(View.GONE);
                    return;
                }
                clickCouterTextView.setText(getString(R.string.сlick_counter_text, ++counter));
                updateCookieStatus();
                setCookieStatus();
                v.startAnimation(cookieZoomAnimation);
                crumbsImageView.startAnimation(crumbsFallingAnimation);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor addCicks = userClickCounterSharedPreferences.edit();
        addCicks.putInt("numberOfClick",counter);
        addCicks.putInt("cookieStatus",status);
        addCicks.apply();
        userClickCounterSharedPreferences = null;
        addClickImageButtonButton = null;
    }

    private void loadClickCounterAndCookieStatus() {
        counter = userClickCounterSharedPreferences.getInt("numberOfClick",0);
        status = userClickCounterSharedPreferences.getInt("cookieStatus",0);
    }

    private void setCookieStatus()
    {
        switch (status)
        {
            case 0 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button); break;}
            case 1 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_10); break;}
            case 2 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_20); break;}
            case 3 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_30); break;}
            case 4 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_40); break;}
            case 5 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_50); break;}
            case 6 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_60); break;}
            case 7 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_70); break;}
            case 8 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_80); break;}
            case 9 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_90); break;}
            case 10 : {addClickImageButtonButton.setImageResource(R.drawable.ic_cookie_button_100); break;}
            default: addClickImageButtonButton.setVisibility(View.GONE);
        }
    }


    private void updateCookieStatus()
    {
        if (counter >= 100 && counter < 200)
            status = 1;
        else if (counter >= 200 && counter < 300)
            status = 2;
        else if (counter >= 300 && counter < 400)
            status = 3;
        else if (counter >= 400 && counter < 500)
            status = 4;
        else if (counter >= 500 && counter < 600)
            status = 5;
        else if (counter >= 600 && counter < 700)
            status = 6;
        else if (counter >= 700 && counter < 800)
            status = 7;
        else if (counter >= 800 && counter < 900)
            status = 8;
        else if (counter >= 900 && counter < 1000)
            status = 9;
        else if (counter >= 1000 && counter < 1100)
            status = 10;
        else
            status = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_button,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.toolbar_button) {
            Intent showOnlyClickCounter = new Intent(this,OnlyClickConterActivity.class);
            showOnlyClickCounter.putExtra("Counter",Integer.toString(counter));
            startActivity(showOnlyClickCounter);
        }
        return super.onOptionsItemSelected(item);
    }

}
