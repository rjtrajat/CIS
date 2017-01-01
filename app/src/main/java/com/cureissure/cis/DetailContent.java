package com.cureissure.cis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.view.View.VISIBLE;

/**
 * Created by RAJAT SINGH on 12/24/2016.
 */

public class DetailContent extends AppCompatActivity {

    TextView detailtitle ;
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

        view = findViewById(R.id.detail_content__parent_about_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_content__parent_experience_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_content__parent_specialization_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_content__parent_mail_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_content__parent_mobile_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.detail_content__parent_address_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.detail_content__parent_name_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_content__parent_about_id);
        viewHeight=(int)(screenHeight*7)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        viewHeight=(int)(screenHeight*11)/100;
        view = findViewById(R.id.detail_content_submit_schedule_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;
    }

    @Override
    protected void onResume() {
        super.onResume();

        detailtitle = (TextView)findViewById(R.id.detail_content_text_title_bar_id);



       if(MainActivity.clicked_id.contains("CIS_DOC")) {
            APIsCall.detailIndividualDoctorAPI(MainActivity.clicked_id);
            detailtitle.setText("Doctor Detail");
        }
        else if(MainActivity.clicked_id.contains("CIS_HOS")) {
            APIsCall.detailIndividualHospitalAPI(MainActivity.clicked_id);
            detailtitle.setText("Hospital Detail");
        }
            else if(MainActivity.clicked_id.contains("CIS_MED"))
        {
            APIsCall.detailIndividualMedicalShopAPI(MainActivity.clicked_id);
            detailtitle.setText("Medical Shop Detail");
            Button button_register = (Button)findViewById(R.id.Button_schedule_Bar);
            button_register.setText("Order Medicine");
        }
        else if(MainActivity.clicked_id.contains("CIS_TEST"))
        {
            APIsCall.detailIndividualTestCenterAPI(MainActivity.clicked_id);
            detailtitle.setText("Test Center Detail");
        }
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
    public void DetailDesign(JSONObject jsonObject){
System.out.println("Response is detail content "+jsonObject);

        String Name = "";
        String About = "" ;
        String Specialization = "";
        String Experience = "";
        String Mail_id = "";
        String Mobile_No = "";
        String Address = "";

        try {
           if (MainActivity.clicked_id.contains("CIS_DOC")) {

                Name = (String) jsonObject.get("doctorname");
                About = (String) jsonObject.get("aboutdoctor");
                Specialization = (String)jsonObject.get("specialization");
                Experience = (String)jsonObject.get("experience");
                Mail_id = (String) jsonObject.get("mailid");
                Mobile_No = (String) jsonObject.get("contact");
                Address = (String) jsonObject.get("fulladdress");

            } else if (MainActivity.clicked_id.contains("CIS_HOS")) {
                Name = (String) jsonObject.get("hospitalname");
                About = (String) jsonObject.get("abouthospital");
                Specialization = (String)jsonObject.get("specialization");
                Experience = (String)jsonObject.get("established");
                Mail_id = (String) jsonObject.get("mailid");
                Mobile_No = (String) jsonObject.get("contact");
                Address = (String) jsonObject.get("fulladdress");

            } else if (MainActivity.clicked_id.contains("CIS_MED")) {

                Name = (String) jsonObject.get("medicalshopname");
                Mail_id = (String) jsonObject.get("mailid");
                Mobile_No = (String) jsonObject.get("contact");
                Address = (String) jsonObject.get("fulladdress");

            } else if (MainActivity.clicked_id.contains("CIS_TEST")) {

                Name = (String) jsonObject.get("testcentername");
                About = (String) jsonObject.get("abouttestcenter");
                Specialization = (String)jsonObject.get("specialization");
                Experience = (String)jsonObject.get("established");
                Mail_id = (String) jsonObject.get("mailid");
                Mobile_No = (String) jsonObject.get("contact");
                Address = (String) jsonObject.get("fulladdress");
            }

            TextView textView = (TextView)findViewById(R.id.detail_content_about_id);
            if(!About.equals("")){
                textView.setText(About);
            }

             textView = (TextView)findViewById(R.id.detail_content_address_id);
            if(!Address.equals("")){
                textView.setText(Address);
            }

            textView = (TextView)findViewById(R.id.detail_content_experience_id);
            if(!Experience.equals("")){
                textView.setText(Experience);
            }


            textView = (TextView)findViewById(R.id.detail_content_mail_id);
            if(!Mail_id.equals("")){
                textView.setText(Mail_id);
            }


            textView = (TextView)findViewById(R.id.detail_content_mobile_id);
            if(!Mobile_No.equals("")){
                textView.setText(Mobile_No);
            }


            textView = (TextView)findViewById(R.id.detail_content_name_id);
            if(!Name.equals("")){
                textView.setText(Name);
            }
            textView = (TextView)findViewById(R.id.detail_content_specialization_id);
            if(!Specialization.equals("")){
                textView.setText(Specialization);
            }

            if(MainActivity.clicked_id.contains("CIS_MED")){
//                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.detail_content__parent_about_id);
//                linearLayout.removeAllViews();
//                linearLayout = (LinearLayout)findViewById(R.id.detail_content__parent_experience_id);
//                linearLayout.removeAllViews();
//                linearLayout = (LinearLayout)findViewById(R.id.detail_content__parent_specialization_id);
//                linearLayout.removeAllViews();


            }

        }
        catch (Exception e){

        }
    }
    public void schedule_button(View view){
//        Button button_register = (Button)findViewById(R.id.Button_schedule_Bar);
//        button_register.setBackgroundColor(Color.parseColor("#13866f"));
//        button_register.setTextColor(Color.parseColor("#FFFFFF"));
        Intent intent = new Intent(this,Schedule_page.class);
        startActivity(intent);
    }

}
