package com.cureissure.cis;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import static android.view.View.VISIBLE;

/**
 * Created by RAJAT SINGH on 11/27/2016.
 */

public class registration extends AppCompatActivity {

    public NumberPicker yearpicker;
    public NumberPicker monthpicker;
    public TextView location_textview;
    public TextView register_year_textview;
    public TextView register_month_textview;
    public static android.support.v7.app.AlertDialog.Builder location_loading_builder;
    public static android.support.v7.app.AlertDialog location_loading_alertDialog;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        getSupportActionBar().hide();
        setContentView(R.layout.registration);
        System.out.println("I am here");
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;
        View view = findViewById(R.id.Register_TitleBar);
        System.out.println("I am here");
        int viewHeight=(int)(screenHeight*10)/100;
        int viewWidth=(int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.Register_back_Title_Bar_Id);
        viewHeight=(int)(screenHeight*3)/100;
        viewWidth = (int)(screenWidth*7)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;


        view = findViewById(R.id.register_linear_name_id );
        viewHeight=(int)(screenHeight*11)/100;
        viewWidth = (int)(screenWidth*80)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.register_linear_email_id );
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.register_linear_mobile_id );
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        viewHeight=(int)(screenHeight*20)/100;
        view = findViewById(R.id.register_linear_experience_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        viewHeight=(int)(screenHeight*11)/100;
        view = findViewById(R.id.register_linear_location_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        viewHeight=(int)(screenHeight*11)/100;
        view = findViewById(R.id. register_linear_submit_register_id);
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;
        location_textview = (TextView) findViewById(R.id.register_location_textview_id);
        location_textview.setText(GeoLocation.Address_Global);

        final String[] year_picker  = new String []{"1985","1986","1987","1988","1989","1990","1991","1992","1993",
                "1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009",
                "2010","2011","2012","2013","2014","2015","2016"};

        final String [] month_picker = new String []{"January","February","March","April","May","June","July","August","September","October","November","December"};

        yearpicker = (NumberPicker)findViewById(R.id.Year_picker);
        monthpicker = (NumberPicker)findViewById(R.id.Month_picker);
        yearpicker.setMinValue(0);
        yearpicker.setMaxValue(year_picker.length-1);
        yearpicker.setDisplayedValues(year_picker);
        //disable soft keyboard
        yearpicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        //set wrap true or false, try it you will know the difference
        yearpicker.setWrapSelectorWheel(false);

        monthpicker.setMinValue(0);
        monthpicker.setMaxValue(month_picker.length-1);
        monthpicker.setDisplayedValues(month_picker);
        //disable soft keyboard
        monthpicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        //set wrap true or false, try it you will know the difference
        monthpicker.setWrapSelectorWheel(false);

        TextView tempnametextview=(TextView) findViewById(R.id.register_name_id);
        TextView tempexptextview=(TextView) findViewById(R.id.register_experience_id);
        if(MainActivity.register_index==0){
        tempnametextview.setText("Doctor Name :");
        tempexptextview.setText("Doctor Since :");
        }
        else if(MainActivity.register_index==1){
            tempnametextview.setText("Hospital Name :");
            tempexptextview.setText("Established :");
        }

        else if(MainActivity.register_index==2){
            tempnametextview.setText("Test Center Name :");
            tempexptextview.setText("Established :");
        }



        register_year_textview = (TextView) findViewById(R.id.register_year);
        register_month_textview = (TextView) findViewById(R.id.register_month);


        yearpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                register_year_textview.setText(year_picker[yearpicker.getValue()]);



            }
        });
        monthpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                register_month_textview.setText(month_picker[monthpicker.getValue()]+" ");



            }
        });
        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);

    }
    public void backtoMain(View view){
        ImageView image_back  =(ImageView) findViewById(R.id.Register_back_Title_Bar_Id);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        image_back.startAnimation(in);
        image_back.setVisibility(VISIBLE);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void register_button(View view){
        Button button_register = (Button)findViewById(R.id.Button_register_Bar);
//        button_register.setBackgroundColor(Color.parseColor("#13866f"));
//        button_register.setTextColor(Color.parseColor("#FFFFFF"));

        String name;
        String mail;
        String contact;
        String experience_year;
        String experience_month;

        EditText editText = (EditText)findViewById(R.id.register_name_edit_id);
        name = editText.getText().toString();
        editText = (EditText)findViewById(R.id.register_email_edit_id);
        mail = editText.getText().toString();
        editText = (EditText)findViewById(R.id.register_mobile_edit_id);
        contact = editText.getText().toString();
        TextView textView = (TextView)findViewById(R.id.register_year) ;
        experience_year = textView.getText().toString();
        textView = (TextView)findViewById(R.id.register_month) ;
        experience_month = textView.getText().toString();


        String msg = "Please Enter ";
        if(name.equals("")||contact.equals("")||mail.equals("")||experience_year.equals("")||experience_month.equals("")) {

            if (name.equals("")) {
                msg = msg + "Name ";
            }
            if (contact.equals("")) {
                msg = msg + "Contact ";
            }
            if (mail.equals("")) {
                msg = msg + "Mail ";
            }
            if (experience_year.equals("") || experience_month.equals("")) {
                msg = msg + "Experience ";
            }

            location_loading_builder.setMessage(msg);
            location_loading_alertDialog = location_loading_builder.create();
            location_loading_alertDialog.show();
        }
        else if(!mail.equals("")){
            if(!isValidEmaillId(mail)){
                location_loading_builder.setMessage("Please Enter correct Mail format ");
                location_loading_alertDialog =  location_loading_builder.create();
                location_loading_alertDialog.show();
            }
        }
        else{


            APIsCall.sendMailDocHosTestMed(name,mail,contact,experience_month,experience_year);

            location_loading_builder.setMessage("Registered for verification , will contact soon");
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
    }

    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public void goToMainBack(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
