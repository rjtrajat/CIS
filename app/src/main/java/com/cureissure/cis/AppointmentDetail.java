package com.cureissure.cis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static android.view.View.VISIBLE;

/**
 * Created by RAJAT SINGH on 12/25/2016.
 */

public class AppointmentDetail extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.detailcontent);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;
        View view = findViewById(R.id.Appointment_Detail_TitleBar);
        System.out.println("I am here");
        int viewHeight = (int) (screenHeight * 10) / 100;
        int viewWidth = (int) (screenWidth * 100) / 100;
        view.getLayoutParams().height = viewHeight;
        view.getLayoutParams().width = viewWidth;

        view = findViewById(R.id.Appointment_Detail_back_Title_Bar_Id);
        viewHeight = (int) (screenHeight * 3) / 100;
        viewWidth = (int) (screenWidth * 7) / 100;
        view.getLayoutParams().height = viewHeight;
        view.getLayoutParams().width = viewWidth;
    }
    public void backtoMain(View view){
        ImageView image_back  =(ImageView) findViewById(R.id.Appointment_Detail_back_Title_Bar_Id);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        image_back.startAnimation(in);
        image_back.setVisibility(VISIBLE);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
