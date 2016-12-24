package com.cureissure.cis;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.view.View.VISIBLE;

/**
 * Created by RAJAT SINGH on 12/24/2016.
 */

public class DetailContent extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.detailcontent);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;
        View view = findViewById(R.id.Detail_Content_TitleBar);
        System.out.println("I am here");
        int viewHeight=(int)(screenHeight*10)/100;
        int viewWidth=(int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.Detail_Content_back_Title_Bar_Id);
        viewHeight=(int)(screenHeight*3)/100;
        viewWidth = (int)(screenWidth*7)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(MainActivity.clicked_id.contains("PATIENT_KEY"))
        APIsCall.detailScheduleAPI(MainActivity.clicked_id);
       else if(MainActivity.clicked_id.contains("CIS_DOC"))
            APIsCall.detailIndividualDoctorAPI(MainActivity.clicked_id);
       else if(MainActivity.clicked_id.contains("CIS_HOS"))
            APIsCall.detailIndividualHospitalAPI(MainActivity.clicked_id);
       else if(MainActivity.clicked_id.contains("CIS_MED"))
            APIsCall.detailIndividualMedicalShopAPI(MainActivity.clicked_id);
        else if(MainActivity.clicked_id.contains("CIS_TEST"))
            APIsCall.detailIndividualTestCenterAPI(MainActivity.clicked_id);
        APIsCall.context = this;
    }

    public void backtoMain(View view){
        ImageView image_back  =(ImageView) findViewById(R.id.Detail_Content_back_Title_Bar_Id);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        image_back.startAnimation(in);
        image_back.setVisibility(VISIBLE);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void DetailDesign(JSONObject response){
System.out.println("Response is detail content "+response);
    }
}
