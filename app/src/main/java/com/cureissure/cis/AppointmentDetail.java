package com.cureissure.cis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.VISIBLE;
import static com.cureissure.cis.APIsCall.context;

/**
 * Created by RAJAT SINGH on 12/25/2016.
 */

public class AppointmentDetail extends AppCompatActivity{

    EditText editTextkeybutton;
    public static android.support.v7.app.AlertDialog.Builder location_loading_builder;
    public static android.support.v7.app.AlertDialog location_loading_alertDialog;
    public String GlobaluniqueAppointmentKey;
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


        view = findViewById(R.id.detail_appointment__parent_uniquekeyappointment_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_nameofpatient_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_contactofpatient_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_mailidofpatient_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.detail_appointment__parent_problemdescriptionofpatient_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_fulladdressofpatient_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_statusvalue_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_statusdatetime_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_timeanddateofappointment_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_paid_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_name_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_specialization_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_experience_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_contact_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_mail_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_about_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.detail_appointment__parent_dochos_fulladdress_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;




        viewHeight=(int)(screenHeight*11)/100;
        view = findViewById(R.id.appointmet_detail_submit_register_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.Button_Title_Appoint_Bar);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*20)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

       LinearLayout linearLayout = (LinearLayout)findViewById(R.id.detail_appointment_complete_detail_id);
        linearLayout.setVisibility(View.INVISIBLE);
        Button button = (Button)findViewById(R.id.Button_Title_Appoint_Bar);
        button.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.detail_appointment_error_id);
        textView.setVisibility(View.INVISIBLE);

         editTextkeybutton = (EditText)findViewById(R.id.appointment_detail_edittext_id);

        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);


    }
    private void hideKeyboard(EditText editText) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(getWindow().getAttributes().token, 0);
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

        hideKeyboard(editTextkeybutton);


        EditText editText = (EditText)findViewById(R.id.appointment_detail_edittext_id);

        String enteredPin = editText.getText().toString();
        System.out.println("Entered pin is "+enteredPin);

        if(!enteredPin.equals("")){

            System.out.println("Response is detail "+editText.getText().toString());
            location_loading_builder.setMessage("Loading Appointment Detail");
            location_loading_alertDialog =  location_loading_builder.create();
            location_loading_alertDialog.setCanceledOnTouchOutside(false);
            location_loading_alertDialog.show();

APIsCall.detailScheduleAPI(editText.getText().toString());
        context = this;
        }
        else{
            location_loading_builder.setMessage("Please Enter CIS Appointment No.");
            location_loading_alertDialog =  location_loading_builder.create();
            location_loading_alertDialog.show();
        }
    }

    public void detailDesign(JSONObject jsonObject){


        location_loading_alertDialog.dismiss();

        System.out.println("Response is coming "+jsonObject);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linear_appointment_detail_id);
        linearLayout.removeAllViews();
      linearLayout = (LinearLayout)findViewById(R.id.detail_appointment_complete_detail_id);
        linearLayout.setVisibility(View.VISIBLE);
        Button button = (Button)findViewById(R.id.Button_Title_Appoint_Bar);
        button.setVisibility(View.VISIBLE);

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

        GlobaluniqueAppointmentKey = uniquekeyappointment;
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
            context = this;
        }
        else if(appointmenttype.equals("HOSPITAL")){
            APIsCall.detailIndividualHospitalAppointAPI(appointmenttypekey);
            context = this;
        }
        else{
            APIsCall.detailIndividualTestCenterAppointAPI(appointmenttypekey);
            context = this;
        }
    }
    catch (Exception e){

        System.out.println("Response is catch "+e);
    }
    }

    public void updateDoctorAppointed(JSONObject jsonObject){

        location_loading_alertDialog.dismiss();

        System.out.println("Response is coming individual");
     //   String cisdocid;
    //   double longitude;
    //    double latitude;
        String fulladdress;
         String aboutdoctor ;
        String mailid ;
         String contact;
         String experience;
        String specialization;
         String doctorname;
        try{
        //    cisdocid = (String) jsonObject.get("cisdocid");
            fulladdress= (String) jsonObject.get("fulladdress");
            aboutdoctor = (String) jsonObject.get("aboutdoctor");
            mailid= (String) jsonObject.get("mailid");
            contact = (String) jsonObject.get("contact");
            experience= (String) jsonObject.get("experience");
            specialization = (String) jsonObject.get("specialization");
            doctorname= (String) jsonObject.get("doctorname");

            TextView textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_name_id);
            textView.setText("Doctor Name :");
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_name_id);
            textView.setText(doctorname);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_specialization_id);
            textView.setText("Specialization : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_specialization_id);
            textView.setText(specialization);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_experience_id);
            textView.setText("Experience : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_experience_id);
            textView.setText(experience.toString());
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_contact_id);
            textView.setText("Contact : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_contact_id);
            textView.setText(contact);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_mail_id);
            textView.setText("Mail Id : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_mail_id);
            textView.setText(mailid);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_about_id);
            textView.setText("About Doctor : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_about_id);
            textView.setText(aboutdoctor);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_fulladdress_id);
            textView.setText("Address : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_fulladdress_id);
            textView.setText(fulladdress);
        }
        catch (Exception e){

        }

    }
    public void updateHospitalAppointed(JSONObject jsonObject){

        location_loading_alertDialog.dismiss();

     //   String cishosid;
      //  double longitude;
      //   double latitude;
        String fulladdress;
        String abouthospital ;
        String mailid ;
         String contact;
         String established ;
         String specialization;
         String hospitalname;
        try{
         //   cishosid = (String) jsonObject.get("cishosid");
            fulladdress= (String) jsonObject.get("fulladdress");
            abouthospital = (String) jsonObject.get("abouthospital");
            mailid= (String) jsonObject.get("mailid");
            contact = (String) jsonObject.get("contact");
            established= (String) jsonObject.get("established");
            specialization = (String) jsonObject.get("specialization");
            hospitalname= (String) jsonObject.get("hospitalname");

            TextView textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_name_id);
            textView.setText("Hospital Name :");
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_name_id);
            textView.setText(hospitalname);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_specialization_id);
            textView.setText("Specialization : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_specialization_id);
            textView.setText(specialization);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_experience_id);
            textView.setText("Established : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_experience_id);
            textView.setText(established.toString());
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_contact_id);
            textView.setText("Contact : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_contact_id);
            textView.setText(contact);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_mail_id);
            textView.setText("Mail Id : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_mail_id);
            textView.setText(mailid);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_about_id);
            textView.setText("About Hospital : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_about_id);
            textView.setText(abouthospital);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_fulladdress_id);
            textView.setText("Address : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_fulladdress_id);
            textView.setText(fulladdress);


        }
        catch (Exception e){

        }
    }

    public void updateTestCenterAppointed(JSONObject jsonObject){

        location_loading_alertDialog.dismiss();

        //   String cishosid;
        //  double longitude;
        //   double latitude;
        String fulladdress;
        String abouthospital ;
        String mailid ;
        String contact;
        String established ;
        String specialization;
        String hospitalname;
        try{
            //   cishosid = (String) jsonObject.get("cishosid");
            fulladdress= (String) jsonObject.get("fulladdress");
            abouthospital = (String) jsonObject.get("abouttestcenter");
            mailid= (String) jsonObject.get("mailid");
            contact = (String) jsonObject.get("contact");
            established= (String) jsonObject.get("established");
            specialization = (String) jsonObject.get("specialization");
            hospitalname= (String) jsonObject.get("testcentername");

            TextView textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_name_id);
            textView.setText("Test Center Name :");
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_name_id);
            textView.setText(hospitalname);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_specialization_id);
            textView.setText("Specialization : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_specialization_id);
            textView.setText(specialization);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_experience_id);
            textView.setText("Established : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_experience_id);
            textView.setText(established.toString());
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_contact_id);
            textView.setText("Contact : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_contact_id);
            textView.setText(contact);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_mail_id);
            textView.setText("Mail Id : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_mail_id);
            textView.setText(mailid);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_about_id);
            textView.setText("About Test Center : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_about_id);
            textView.setText(abouthospital);
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_one_fulladdress_id);
            textView.setText("Address : " );
            textView = (TextView)findViewById(R.id.detail_appointment_dochos_fulladdress_id);
            textView.setText(fulladdress);


        }
        catch (Exception e){

        }
    }

    public void notFoundId(){

        location_loading_alertDialog.dismiss();

        TextView textView = (TextView) findViewById(R.id.detail_appointment_error_id);
        textView.setVisibility(View.VISIBLE);
    }


    protected void Button_Title_On_Title_Bar(View view){
        Button button_title  =(Button) findViewById(R.id.Button_Title_Appoint_Bar);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        button_title.startAnimation(in);
        button_title.setVisibility(VISIBLE);

        context = this;
        APIsCall.deleteAppointment(GlobaluniqueAppointmentKey);

    }

    public void appointmentDeleted(){
        location_loading_builder.setMessage("Appointment Cancelled");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);
        location_loading_alertDialog.show();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                goToMainBackFromAppointment();
            }
        },2000);
    }

    public void goToMainBackFromAppointment(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
