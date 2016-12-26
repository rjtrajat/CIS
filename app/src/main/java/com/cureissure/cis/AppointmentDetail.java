package com.cureissure.cis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import static android.view.View.VISIBLE;

/**
 * Created by RAJAT SINGH on 12/25/2016.
 */

public class AppointmentDetail extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.appointmentdetail);
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

        view = findViewById(R.id.linear_appointment_detail_id);
      //  viewHeight = (int) (screenHeight * 3) / 100;
        viewWidth = (int) (screenWidth * 70) / 100;
      //  view.getLayoutParams().height = viewHeight;
        view.getLayoutParams().width = viewWidth;

       LinearLayout linearLayout = (LinearLayout)findViewById(R.id.detail_appointment_complete_detail_id);
        linearLayout.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.detail_appointment_error_id);
        textView.setVisibility(View.INVISIBLE);

    }
    public void backtoMain(View view){
        ImageView image_back  =(ImageView) findViewById(R.id.Appointment_Detail_back_Title_Bar_Id);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        image_back.startAnimation(in);
        image_back.setVisibility(VISIBLE);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void check_button(View view){

        EditText editText = (EditText)findViewById(R.id.appointment_detail_edittext_id);

        if(!editText.toString().equals("")){

            System.out.println("Response is detail "+editText.getText().toString());


APIsCall.detailScheduleAPI(editText.getText().toString());
        APIsCall.context = this;
        }
    }

    public void detailDesign(JSONObject jsonObject){

        System.out.println("Response is coming "+jsonObject);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_appointment_detail_id);
        linearLayout.removeAllViews();
      linearLayout = (LinearLayout)findViewById(R.id.detail_appointment_complete_detail_id);
        linearLayout.setVisibility(View.VISIBLE);

         String uniquekeyappointment;
         String nameofpatient;
         String contactofpatient;
         String mailidofpatient;
         double longitudeofpatient;
         double latitudeofpatient;
         String problemdescriptionofpatient;
         String fulladdressofpatient;
         String statusvalue;
         String statusdatetime ;
         String timeanddateofappointment ;
         Boolean paid ;
         String appointmenttype;
         String appointmenttypekey;
    try {
        uniquekeyappointment = (String) jsonObject.get("uniquekeyappointment");
        nameofpatient = (String) jsonObject.get("nameofpatient");
        contactofpatient = (String) jsonObject.get("contactofpatient");
        mailidofpatient = (String) jsonObject.get("mailidofpatient");
      //  longitudeofpatient = (double) jsonObject.get("longitudeofpatient");
        //latitudeofpatient = (double) jsonObject.get("latitudeofpatient");
        problemdescriptionofpatient = (String) jsonObject.get("problemdescriptionofpatient");
        fulladdressofpatient = (String) jsonObject.get("fulladdressofpatient");
        statusvalue = (String) jsonObject.get("statusvalue");
        statusdatetime = (String) jsonObject.get("statusdatetime");
        timeanddateofappointment = (String) jsonObject.get("timeanddateofappointment");
        paid = (Boolean) jsonObject.get("paid");
        appointmenttype = (String) jsonObject.get("appointmenttype");
        appointmenttypekey = (String) jsonObject.get("appointmenttypekey");

        TextView textView = (TextView)findViewById(R.id.detail_appointment_uniquekeyappointment_id);
        textView.setText(uniquekeyappointment);
        textView = (TextView)findViewById(R.id.detail_appointment_nameofpatient_id);
        textView.setText(nameofpatient);
        textView = (TextView)findViewById(R.id.detail_appointment_contactofpatient_id);
        textView.setText(contactofpatient);
        textView = (TextView)findViewById(R.id.detail_appointment_mailidofpatient_id);
        textView.setText(mailidofpatient);
        textView = (TextView)findViewById(R.id.detail_appointment_problemdescriptionofpatient_id);
        textView.setText(problemdescriptionofpatient);
        textView = (TextView)findViewById(R.id.detail_appointment_fulladdressofpatient_id);
        textView.setText(fulladdressofpatient);
        textView = (TextView)findViewById(R.id.detail_appointment_statusvalue_id);
        textView.setText(statusvalue);
        textView = (TextView)findViewById(R.id.detail_appointment_statusdatetime_id);
        textView.setText(statusdatetime);
        textView = (TextView)findViewById(R.id.detail_appointment_timeanddateofappointment_id);
        textView.setText(timeanddateofappointment);
        textView = (TextView)findViewById(R.id.detail_appointment_paid_id);
        textView.setText(paid.toString());

        if(appointmenttype.equals("DOCTOR")) {
            APIsCall.detailIndividualDoctorAppointAPI(appointmenttypekey);
            APIsCall.context = this;
        }
        else{
            APIsCall.detailIndividualHospitalAppointAPI(appointmenttypekey);
            APIsCall.context = this;
        }
    }
    catch (Exception e){

        System.out.println("Response is catch "+e);
    }
    }

    public void updateDoctorHospitalAppointed(JSONObject jsonObject){

        
    }

    public void notFoundId(){
        TextView textView = (TextView) findViewById(R.id.detail_appointment_error_id);
        textView.setVisibility(View.VISIBLE);
    }
}
