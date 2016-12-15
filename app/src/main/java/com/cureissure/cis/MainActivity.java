package com.cureissure.cis;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {
    WifiGPS wifigps;
    public   LocationManager locationManager;
    public ConnectivityManager connectivityManager;
    public AlertDialog.Builder alertDialogBuilder;
    public android.support.v7.app.AlertDialog.Builder builder;
    public static android.support.v7.app.AlertDialog.Builder location_loading_builder;
    public static android.support.v7.app.AlertDialog location_loading_alertDialog;


    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    //this is flag for checking geo location access
    public static boolean check_for_geo = false;
    boolean visible = false;

    //for drawer begin
    static public int max_screen_height ;
    static public int max_screen_width;
    private static String TAG = MainActivity.class.getSimpleName();
    AlertDialog.Builder builder_register ;

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private TextView screen_half;

    TextView Location_subtitle_textview;

    //for drawer end
    public static int register_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;
        View view = findViewById(R.id.MainActivity_TitleBar);
        int viewHeight=(int)(screenHeight*10)/100;
        int viewWidth=(int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;
        view = findViewById(R.id.Button_Title_Bar);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*25)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.Menu_Title_Bar_Id);
        viewHeight=(int)(screenHeight*3)/100;
        viewWidth = (int)(screenWidth*7)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;




        view = findViewById(R.id.drawerLayout);
        viewHeight=(int)(screenHeight*90)/100;
        viewWidth = (int)(screenWidth*70)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;
        max_screen_height = (int)(screenHeight*90)/100;
        max_screen_width = (int)(screenWidth*70)/100;


        view = findViewById(R.id.screen_half_id);
        viewHeight=(int)(screenHeight*90)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        //Dialog box for loading

        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_builder.setMessage("Location loading ...");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);

        //This is done for marshmallow

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && WifiGPS.gps_access_granted==false){
            builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setTitle("This app needs location access");
            builder.setMessage("Please grant location access ");
            builder.setPositiveButton(android.R.string.ok,null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener(){
                public void onDismiss(DialogInterface dialog){
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                    WifiGPS.gps_access_granted = true;
                    System.out.println("value is "+WifiGPS.gps_access_granted);
                       location_loading_alertDialog.show();
                    check_for_geo = true;
                }

            });
            builder.show();
        }
        else{
            check_for_geo = true;
        }

        //for drawer begin
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        screen_half = (TextView) findViewById(R.id.screen_half_id);
        // Populate the Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);

        Location_subtitle_textview = (TextView) findViewById(R.id.subTitle_change_location);
        //for drawer end

        //pop message option for register begin
        CharSequence colors[] = new CharSequence[] {"Doctor", "Hospital", "Test Center"};
        builder_register = new AlertDialog.Builder(this);
        builder_register.setTitle("Select for Registration");
        builder_register.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                register_index = which;
                onClickRegister();
            }
        });

        //pop message option for register end
        visible = false;
        mDrawerLayout.setVisibility(View.GONE);
        final int initialWidth = mDrawerLayout.getMeasuredWidth();
        mDrawerLayout.setX(-(int)initialWidth);
        screen_half.setX(0);

        //change location variable

         wifigps = new WifiGPS();

    }

    void onClickRegister(){
        Intent myIntent = new Intent(this, registration.class);
        startActivity(myIntent);
    }


    @Override
    protected void onResume(){
        super.onResume();
        WifiGPS.ViewForGPSWiFi = this;
        alertDialogBuilder = new AlertDialog.Builder(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //this if condition after clicking ok for geo location this will execute
        if(check_for_geo==true) {
            if(GeoLocation.Address_Global==null) {
        wifigps.checkGPSLocation(locationManager, connectivityManager, alertDialogBuilder);
            }
            else{
                APIsCall.detailDoctorAPI();
            }

        }
    }


    void RegisterClick(View view){
        builder_register.show();
    }
    void ScheduleClick(View view){
        APIsCall.detailScheduleAPI();
    }
    void MedicalShopClick(View view){
        APIsCall.detailMedicalShopAPI();
    }
    void TestCenterClick(View view){
        APIsCall.detailTestCenterAPI();
    }


    void Aboutclick(View view){
        Intent intent = new Intent(this,Aboutus.class);
        startActivity(intent);
    }
    void Change_location_click(View view){
        location_loading_alertDialog.show();
        visible=false;
        GeoLocation.Address_Global=null;
        collapse(mDrawerLayout,screen_half,true);
    }

    protected void Button_On_Title_Bar(View view){
        Button button_title  =(Button) findViewById(R.id.Button_Title_Bar);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        button_title.startAnimation(in);
        button_title.setVisibility(VISIBLE);
        String text_button_title = (String)button_title.getText();
        if(text_button_title.equals("DOCTORS")){
            button_title.setText("HOSPITALS");
            if(visible==true) {
                collapse(mDrawerLayout,screen_half,false);
                visible=false; visible=false;
            }
            WifiGPS.ViewForGPSWiFi = this;
            if(GeoLocation.Address_Global==null) {
                location_loading_alertDialog.show();
                wifigps.checkGPSLocation(locationManager, connectivityManager, alertDialogBuilder);
            }
            else{
                APIsCall.detailDoctorAPI();
            }
        }
        else{
            button_title.setText("DOCTORS");
            if(visible==true) {
                collapse(mDrawerLayout,screen_half,false);
                visible=false;
            }
            WifiGPS.ViewForGPSWiFi = this;
            if(GeoLocation.Address_Global==null) {
                location_loading_alertDialog.show();
                wifigps.checkGPSLocation(locationManager, connectivityManager, alertDialogBuilder);

            }
            else{
                APIsCall.detailHospitalAPI();
            }


        }

        }



    protected void Menu_Title_Bar(View view){
        final ImageView viewToAnimate = (ImageView) findViewById(R.id.Menu_Title_Bar_Id);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        viewToAnimate.startAnimation(in);
        viewToAnimate.setVisibility(VISIBLE);
        if(visible==true) {
            collapse(mDrawerLayout,screen_half,false);
            visible=false;
        }
        else{
            Location_subtitle_textview.setText("Current Location :   "+GeoLocation.Address_Global);
            expand(mDrawerLayout);
            visible = true;
        }
    }

    /*
* Called when a particular item from the navigation drawer
* is selected.
* */
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.MainActivity_TitleBar, fragment)
                .commit();
        visible=false;
        collapse(mDrawerLayout,screen_half,false);
    }


    public  void expand(final View v) {
        final int targetHeight =max_screen_height;
        final int targetWidth =max_screen_width;
        final View screen_half = findViewById(R.id.screen_half_id);
        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
     //   v.getLayoutParams().height = 1;
    //    v.getLayoutParams().width = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
              //  v.getLayoutParams().height = (int)(targetHeight * interpolatedTime);
               // v.getLayoutParams().width = (int)(targetWidth * interpolatedTime);
                v.setX( (int)(-targetWidth + (int)(targetWidth * interpolatedTime)));
                screen_half.setX((int)(targetWidth * interpolatedTime));
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        // 1dp/ms
     //   a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration((int)(targetWidth / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
    public  void collapse(final View v,final  View screen_half,boolean change_location_using_change_location) {
        final int initialWidth = v.getMeasuredWidth();
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                  //  v.setVisibility(View.GONE);
                }else{
                  //  v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.setX( - (int)(initialWidth * interpolatedTime));
                    screen_half.setX((int)(initialWidth - (int)(initialWidth * interpolatedTime ) -1));

                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
      //  a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration((int)(initialWidth / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);

        if(change_location_using_change_location==true){
           wifigps.checkGPSLocation(locationManager,connectivityManager,alertDialogBuilder);
        }

    }

}
