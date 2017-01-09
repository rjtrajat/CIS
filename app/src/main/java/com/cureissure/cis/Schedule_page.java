package com.cureissure.cis;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;


import static android.view.View.VISIBLE;
import static com.cureissure.cis.DetailContent.DocHosTestDaysClosedGlobal;
import static com.cureissure.cis.DetailContent.DocHosTestOpenTimeGlobal;
import static com.cureissure.cis.MainActivity.clicked_id;

/**
 * Created by RAJAT SINGH on 12/25/2016.
 */

public class Schedule_page extends AppCompatActivity{

    public static android.support.v7.app.AlertDialog.Builder location_loading_builder;
    public static android.support.v7.app.AlertDialog location_loading_alertDialog;
    TextView imageDatepicker ;
    TextView imageTimepicker;
    TextView txtDate, txtTime;

    public String MailPatient;
    public String ContactPatient;
     public  int mYear, mMonth, mDay, mHour, mMinute,mDayWeek;
    int weekDayIndex ;

    int GlobalYear,GlobalMonth,GlobalDay,GlobalWeekDay;
    int GlobalHour, GlobalMinute;

    String uniquekeyappointmentGlobal ;
    String nameofpatientGlobal ;
    String contactofpatientGlobal ;
    String mailidofpatientGlobal;
    Double longitudeofpatientGlobal;
    Double latitudeofpatientGlobal;
    String problemdescriptionofpatientGlobal;
    String fulladdressofpatientGlobal;
    String statusvalueGlobal;
    String statusdatetimeGlobal;
    String dateofappointmentGlobal;
    String timeofappointmentGlobal;
    Boolean paidGlobal;
    String appointmenttypeGlobal;
    String appointmenttypekeyGlobal;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.schedule_page);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;
        View view = findViewById(R.id.Schedule_Page_TitleBar);
        System.out.println("I am here");
        int viewHeight=(int)(screenHeight*10)/100;
        int viewWidth=(int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.Schedule_Page_back_Title_Bar_Id);
        viewHeight=(int)(screenHeight*3)/100;
        viewWidth = (int)(screenWidth*7)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.schedule_linear_name_id);
        viewHeight=(int)(screenHeight*11)/100;
        viewWidth = (int)(screenWidth*80)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        viewHeight=(int)(screenHeight*6)/100;
        view = findViewById(R.id.schedule_linear_date_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        viewHeight=(int)(screenHeight*6)/100;
        view = findViewById(R.id.schedule_linear_time_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        viewHeight=(int)(screenHeight*11)/100;
        view = findViewById(R.id.schedule_linear_email_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.schedule_linear_mobile_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.schedule_linear_description_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        viewHeight=(int)(screenHeight*11)/100;
        view = findViewById(R.id.schedule_linear_submit_register_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        imageDatepicker = (TextView) findViewById(R.id.schedule_date_image_id);
        imageTimepicker = (TextView) findViewById(R.id.schedule_time_image_id);

        txtDate = (TextView)findViewById(R.id.schedule_date_value_image_id);
        txtTime  = (TextView)findViewById(R.id.schedule_time_value_image_id);

        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_alertDialog =  location_loading_builder.create();


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

    }
    public void backtoDetailContent(View view){
        ImageView image_back  =(ImageView) findViewById(R.id.Schedule_Page_back_Title_Bar_Id);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        image_back.startAnimation(in);
        image_back.setVisibility(VISIBLE);

        Intent intent = new Intent(this,DetailContent.class);
        startActivity(intent);
    }
    public void schedule_button(View view){

        String nameofpatient ;
        String contactofpatient ;
        String mailidofpatient;
        String problemdescriptionofpatient;
        String dateofappointment;
        String timeofappointment;

        EditText editText = (EditText)findViewById(R.id.schedule_name_edit_id);
        nameofpatient = editText.getText().toString();

        editText = (EditText)findViewById(R.id.schedule_mobile_edit_id);
        contactofpatient = editText.getText().toString();

        editText = (EditText)findViewById(R.id.schedule_email_edit_id);
        mailidofpatient = editText.getText().toString();

        TextView textView = (TextView) findViewById(R.id.schedule_date_value_image_id);
        dateofappointment = textView.getText().toString();
        textView = (TextView) findViewById(R.id.schedule_time_value_image_id);
        timeofappointment = textView.getText().toString();

        editText = (EditText)findViewById(R.id.schedule_description_edit_id);
        problemdescriptionofpatient = editText.getText().toString();



        if(nameofpatient.equals("")||mailidofpatient.equals("")||contactofpatient.equals("")||problemdescriptionofpatient.equals("")||dateofappointment.equals("")||timeofappointment.equals(""))
        {

            String msg = "Please Enter ";
            if(nameofpatient.equals("")){
                msg = msg+"Patient Name ";
            }
            if(mailidofpatient.equals("")){
                msg = msg+"Mail ";
            }
            if(contactofpatient.equals("")){
                msg = msg+"Contact ";
            }
            if(problemdescriptionofpatient.equals("")){
                msg = msg+"Description ";
            }
            if(dateofappointment.equals("")){
                msg = msg+"Date ";
            }
            if(timeofappointment.equals("")){
                msg = msg+"Time ";
            }
            location_loading_builder.setMessage(msg);
            location_loading_alertDialog =  location_loading_builder.create();
            location_loading_alertDialog.show();
        }
        else if(!mailidofpatient.equals("")){
if(!isValidEmaillId(mailidofpatient)){
    location_loading_builder.setMessage("Please Enter correct Mail format ");
    location_loading_alertDialog =  location_loading_builder.create();
    location_loading_alertDialog.show();
}



else {

try {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    String monthpre="";
    String daypre="";

    if(GlobalMonth<10){
        monthpre="0";
    }
    if(GlobalDay<10){
        daypre="0";
    }



        final Date     date = dateFormat.parse(GlobalYear + "-"+monthpre + GlobalMonth + "-"+daypre + GlobalDay);
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    weekDayIndex = calendar.get(Calendar.DAY_OF_WEEK);
    System.out.println("Response is week day date"+GlobalYear + "-"+monthpre + GlobalMonth + "-"+daypre + GlobalDay + " index "+weekDayIndex);

    weekDayIndex--;

    String[] allday = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    String choosenDay = allday[weekDayIndex];

    String[]   closeddayslist =  DocHosTestDaysClosedGlobal.split(",");


    int i = 0;
    boolean flag = true;

    while(closeddayslist.length>i){
        if(choosenDay.equals(closeddayslist[i].trim())){
            flag= false;
            break;
        }
        i++;
    }

    if(flag==false){
        location_loading_builder.setMessage("Please choose another day , closed on "+closeddayslist[i]);
        location_loading_alertDialog = location_loading_builder.create();
        location_loading_alertDialog.show();
    }

else {
        String[]   opentime = DocHosTestOpenTimeGlobal.split(",");
        boolean flagtime = false;

        Double range = GlobalHour*1.0 + (GlobalMinute*1.0)/(60.0);

        int j  = 0;

        while(opentime.length>j){

            System.out.println("Response is range "+opentime[j]);

            String[] rangetime = opentime[j].split("-");
            int timeone , timetwo;

            if(rangetime[0].contains("PM")){
                timeone = Integer.parseInt(rangetime[0].replace("PM"," ").trim()) + 12;
            }
            else{
                timeone = Integer.parseInt((rangetime[0].replace("AM"," ")).trim());
            }

            if(rangetime[1].contains("PM")){
                timetwo = Integer.parseInt(rangetime[1].replace("PM"," ").trim()) + 12;
            }
            else{
                timetwo = Integer.parseInt(rangetime[1].replace("AM"," ").trim());
            }

            System.out.println("Response is timeone timetwo "+timeone+" "+timetwo);

            if(range>=timeone&&range<=timetwo){
                flagtime = true;
                break;
            }


            j++;


        }

        if(flagtime==false){
            location_loading_builder.setMessage("Appointment Time should be "+DocHosTestOpenTimeGlobal);
            location_loading_alertDialog = location_loading_builder.create();
            location_loading_alertDialog.show();

        }
else {
            DateFormat dateFormatselect = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            String hourpre="";
            String minpre="";

            if(GlobalHour<10){
                hourpre="0";

            }

            if(GlobalDay<10){
                minpre="0";
            }


            final Date     selectedDate = dateFormatselect.parse(GlobalYear + "-"+monthpre + GlobalMonth + "-"+daypre + GlobalDay+" "+hourpre+GlobalHour+":"+minpre+GlobalMinute);
          final Date currentDate = Calendar.getInstance().getTime();

            boolean flagselected = false;

            System.out.println("Response is time time "+ GlobalYear + "-"+monthpre + GlobalMonth + "-"+daypre + GlobalDay+" "+hourpre+GlobalHour+":"+minpre+GlobalMinute);
            System.out.println("Response is time "+currentDate+" "+selectedDate);

            if(selectedDate.compareTo(currentDate)>0){
                flagselected = true;
          }

        if(flagselected == false){
            location_loading_builder.setMessage("Please Select time after current time ");
            location_loading_alertDialog = location_loading_builder.create();
            location_loading_alertDialog.show();
        }
            else {

            location_loading_builder.setMessage("Appointment Schedulling");
            location_loading_alertDialog = location_loading_builder.create();
            location_loading_alertDialog.show();
            APIsCall.getLatestUniquePatientKey();
            APIsCall.context = this;
        }
        }
    }

}
catch (Exception e){

}

}
        }

    }



    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email.trim()).matches();
    }

    public void schedule_update(JSONObject jsonObject){
try {
    String uniquekeyappointment = (String) jsonObject.get("uniquekeyappointment");
    String parseNumber = uniquekeyappointment.replace("PATIENT_KEY_","");
    Long number = Long.parseLong(parseNumber);
    number++;
    uniquekeyappointment = "PATIENT_KEY_"+number;
    String nameofpatient ;
    String contactofpatient ;
    String mailidofpatient;
    Double longitudeofpatient;
    Double latitudeofpatient;
    String problemdescriptionofpatient;
    String fulladdressofpatient;
    String statusvalue;
    String statusdatetime;
    String dateofappointment;
    String timeofappointment;
    Boolean paid;
    String appointmenttype = "";
    String appointmenttypekey;

    EditText editText = (EditText)findViewById(R.id.schedule_name_edit_id);
    nameofpatient = editText.getText().toString();
    editText = (EditText)findViewById(R.id.schedule_mobile_edit_id);
    contactofpatient = editText.getText().toString();
    ContactPatient = contactofpatient;
    editText = (EditText)findViewById(R.id.schedule_email_edit_id);
    mailidofpatient = editText.getText().toString();
    MailPatient = mailidofpatient;
    longitudeofpatient = GeoLocation.Longitude_user;
    latitudeofpatient = GeoLocation.Latititude_user;
    editText = (EditText)findViewById(R.id.schedule_description_edit_id);
    problemdescriptionofpatient = editText.getText().toString();
    fulladdressofpatient = GeoLocation.Address_Global;
    statusvalue = "Booked";
    statusdatetime = "31-11-2016";
    TextView textView = (TextView) findViewById(R.id.schedule_date_value_image_id);
    dateofappointment = textView.getText().toString();
   textView = (TextView) findViewById(R.id.schedule_time_value_image_id);
    timeofappointment = textView.getText().toString();
    paid = false;
    if(MainActivity.clicked_id.contains("CIS_DOC")){
        appointmenttype = "DOCTOR";
    }
    if(MainActivity.clicked_id.contains("CIS_HOS")){
        appointmenttype = "HOSPITAL";
    }
    if(MainActivity.clicked_id.contains("CIS_TEST")){
        appointmenttype = "TESTCENTER";
    }
    appointmenttypekey = MainActivity.clicked_id;



     uniquekeyappointmentGlobal = uniquekeyappointment;
     nameofpatientGlobal =  nameofpatient;
     contactofpatientGlobal = contactofpatient;
     mailidofpatientGlobal = mailidofpatient;
     longitudeofpatientGlobal = longitudeofpatient;
     latitudeofpatientGlobal = latitudeofpatient;
     problemdescriptionofpatientGlobal = problemdescriptionofpatient;
     fulladdressofpatientGlobal = fulladdressofpatient;
     statusvalueGlobal = statusvalue;
     statusdatetimeGlobal = statusdatetime;
     dateofappointmentGlobal = dateofappointment;
     timeofappointmentGlobal = timeofappointment;
     paidGlobal = paid;
     appointmenttypeGlobal = appointmenttype;
     appointmenttypekeyGlobal = appointmenttypekey;


    if(nameofpatient.equals("")||mailidofpatient.equals("")||contactofpatient.equals("")||problemdescriptionofpatient.equals("")||dateofappointment.equals("")||timeofappointment.equals(""))
    {
//        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
//        location_loading_alertDialog =  location_loading_builder.create();
        String msg = "Please Enter ";
        if(nameofpatient.equals("")){
            msg = msg+"Patient Name ";
        }
        if(mailidofpatient.equals("")){
            msg = msg+"Mail ";
        }
        if(contactofpatient.equals("")){
            msg = msg+"Contact ";
        }
        if(problemdescriptionofpatient.equals("")){
            msg = msg+"Description ";
        }
        if(dateofappointment.equals("")){
            msg = msg+"Date ";
        }
        if(timeofappointment.equals("")){
            msg = msg+"Time ";
        }
        location_loading_builder.setMessage(msg);
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.show();
    }
else {
        APIsCall.setPatientDetailentry(uniquekeyappointment, nameofpatient, contactofpatient, mailidofpatient, longitudeofpatient, latitudeofpatient, problemdescriptionofpatient, fulladdressofpatient, statusvalue, statusdatetime, dateofappointment, timeofappointment, paid, appointmenttype, appointmenttypekey);
        APIsCall.context = this;
    }
}
catch (Exception e){

}
    }

    public void imageTimepicker(View view){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);

                        GlobalHour  = hourOfDay;
                        GlobalMinute = minute;

                    }
                }, mHour, mMinute, false);


        timePickerDialog.show();


    }
    public void imageDatepicker(View view){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mDayWeek = c.get(Calendar.DAY_OF_WEEK);



        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth)  {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            GlobalDay = dayOfMonth;
                        GlobalMonth = monthOfYear + 1;
                        GlobalYear = year;

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    public void goToMain(){



        APIsCall.sendMailPatient(uniquekeyappointmentGlobal,nameofpatientGlobal,contactofpatientGlobal,mailidofpatientGlobal,problemdescriptionofpatientGlobal,fulladdressofpatientGlobal,statusvalueGlobal,dateofappointmentGlobal,timeofappointmentGlobal,appointmenttypeGlobal,appointmenttypekeyGlobal,DetailContent.DocHosTestNameGlobal,DetailContent.DocHosTestAboutGlobal,DetailContent.DocHosTestAddressGlobal,DetailContent.DocHosTestExperienceGlobal,DetailContent.DocHosTestMail_idGlobal,DetailContent.DocHosTestMobile_NoGlobal,DetailContent.DocHosTestSpecializationGlobal);




       location_loading_alertDialog.dismiss();
        location_loading_builder.setMessage("Appointment Scheduled , please check your mail");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);
        location_loading_alertDialog.show();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                goToMainBack();
            }
        },3000);


    }
    public void goToMainBack(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
