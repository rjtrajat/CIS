package com.cureissure.cis;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.view.View.VISIBLE;
import static com.cureissure.cis.APIsCall.context;


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
    private LinearLayout screen_half;

    TextView Location_subtitle_textview;

    //for drawer end
    public static int register_index;

    int screenHeight;
    int screenWidth ;

    //Clicked on list :Id
    static public String clicked_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
         screenHeight = displaymetrics.heightPixels;
         screenWidth = displaymetrics.widthPixels;
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
        viewHeight=(int)(screenHeight*86.5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;

        view = findViewById(R.id.screen_half_textview_id);
        viewHeight=(int)(screenHeight*5)/100;
        viewWidth = (int)(screenWidth*100)/100;
        view.getLayoutParams().height=viewHeight;
        view.getLayoutParams().width=viewWidth;





        //Dialog box for loading

        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_builder.setMessage("Location loading ...");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);

        //This is done for marshmallow

        context = this;

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


                    //context = this;
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
        screen_half = (LinearLayout) findViewById(R.id.screen_half_id);
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
                context = this;
            }

        }
    }


    void RegisterClick(View view){
        builder_register.show();
        context = this;
    }
    void ScheduleClick(View view){
       // APIsCall.detailScheduleAPI();
       // APIsCall.context = this;
        Intent intent = new Intent(this,AppointmentDetail.class);
        startActivity(intent);
    }
    void MedicalShopClick(View view){
        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_builder.setMessage("Loading , please wait");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);
        location_loading_alertDialog.show();

        if(visible==true) {
            collapse(mDrawerLayout,screen_half,false);
            visible=false;
        }

        APIsCall.detailMedicalShopAPI();
        context = this;
    }
    void TestCenterClick(View view){

        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_builder.setMessage("Loading , please wait");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);
        location_loading_alertDialog.show();

        if(visible==true) {
            collapse(mDrawerLayout,screen_half,false);
            visible=false;
        }

        APIsCall.detailTestCenterAPI();
        context = this;
    }


    void Aboutclick(View view){
        Intent intent = new Intent(this,Aboutus.class);
        startActivity(intent);
    }
    void Change_location_click(View view){
        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_builder.setMessage("Loading , please wait");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);
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

        location_loading_builder = new android.support.v7.app.AlertDialog.Builder(this);
        location_loading_builder.setMessage("Loading , please wait");
        location_loading_alertDialog =  location_loading_builder.create();
        location_loading_alertDialog.setCanceledOnTouchOutside(false);
        location_loading_alertDialog.show();

        if(text_button_title.equals("DOCTORS")){
            button_title.setText("HOSPITALS");
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
                APIsCall.detailDoctorAPI();
                context = this;
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
                context = this;
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

            if(GeoLocation.Address_Global==null)
                GeoLocation.Address_Global="";
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

    public void TableDesign(JSONArray response , String type){

        location_loading_alertDialog.dismiss();

        System.out.println("Response is table design");
        TextView view = (TextView)findViewById(R.id.screen_half_textview_id);
        view.setText("List of "+type);

        String Id = "";
        String Name = "";
        String Nametag = "";
        String Specialization = "";
        String Specialiazationtag = "";
        String Location = "";
        String Locationtag = "";
        Double Distance ;
        String Distancetag = "Distance : ";
        String fees = "";
        Double Rating ;
        String image = "";
        Double long_list;
        Double lat_list;
        Double long_user = GeoLocation.Longitude_user;
        Double lat_user = GeoLocation.Latititude_user;

        int len = response.length();
        int  i = 0;
        JSONObject jsonObject;

        TableLayout tablelayout = (TableLayout)findViewById(R.id.tablelayout_id);
        tablelayout.removeAllViews();

        if(len==0)
        view.setText("No CIS Registered "+type+"found nearby");
        while(len>i){
            try {
                jsonObject = response.getJSONObject(i);
                if (type.equals("Doctors")) {

                    Nametag="Name : ";
                    Name = (String) jsonObject.get("doctorname");
                    Id = (String) jsonObject.get("cisdocid");
                    fees = (String)jsonObject.get("fees");
                }
                else if (type.equals("Hospitals")) {
                    Nametag="Name : ";
                    Name = (String) jsonObject.get("hospitalname");
                    Id = (String) jsonObject.get("cishosid");
                    fees = (String)jsonObject.get("fees");
                }
                else if (type.equals("MedicalShops")) {
                    Nametag="Name : ";
                    Name = (String) jsonObject.get("medicalshopname");
                    Id = (String) jsonObject.get("cismedid");
                }
                else if (type.equals("TestCenters")) {
                    Nametag="Name : ";
                    Name = (String) jsonObject.get("testcentername");
                    Id = (String) jsonObject.get("cistestid");
                    fees = (String)jsonObject.get("fees");
                }

                Locationtag = "Address : ";
                Location = (String)jsonObject.get("fulladdress");
                long_list = (Double) jsonObject.get("longitude");
                lat_list = (Double)jsonObject.get("latitude");
                image = (String )jsonObject.get("imagethumb");
                Rating = (Double)jsonObject.get("rating");


                Distance =  CalculateDistance.distanceCal(long_user,lat_user,long_list,lat_list);


                TableRow tr = new TableRow(this);

                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT));

                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                 View item = inflater.inflate(R.layout.rowlayout, tr, false);
                int viewWidth = (int)(screenWidth*90);
                item.getLayoutParams().width=viewWidth;

           final     TextView textViewImage = (TextView)item.findViewById(R.id.rowlayout_image_id);
                TextView textViewname = (TextView)item.findViewById(R.id.rowlayout_linear_name_id);
                TextView textViewnametag = (TextView)item.findViewById(R.id.rowlayout_linear_name_tag_id);
                TextView textViewspecialization = (TextView)item.findViewById(R.id.rowlayout_linear_specialization_id);
                TextView textViewspecializationtag = (TextView)item.findViewById(R.id.rowlayout_linear_specialization_tag_id);
                TextView textViewaddress = (TextView)item.findViewById(R.id.rowlayout_linear_address_id);
                TextView textViewaddresstag = (TextView)item.findViewById(R.id.rowlayout_linear_address_tag_id);
                TextView textViewdistance = (TextView)item.findViewById(R.id.rowlayout_linear_distance_id);
                TextView textViewdistancetag = (TextView)item.findViewById(R.id.rowlayout_linear_distance_tag_id);
                TextView textViewfees = (TextView)item.findViewById(R.id.rowlayout_linear_fees_id);
                TextView imageViewone = (TextView)item.findViewById(R.id.starlogoone);
                TextView imageViewtwo = (TextView)item.findViewById(R.id.starlogotwo);
                TextView imageViewthree = (TextView)item.findViewById(R.id.starlogothree);
                TextView imageViewfour = (TextView)item.findViewById(R.id.starlogofour);
                TextView imageViewfive = (TextView)item.findViewById(R.id.starlogofive);

                if(!type.equals("MedicalShops")) {
                    Specialiazationtag = "Specialization : " ;
                            Specialization = (String) jsonObject.get("specialization");
                    textViewspecialization.setText(Specialization);
                    textViewspecializationtag.setText(Specialiazationtag);
                    textViewfees.setText(fees);
                }
                else{
                    textViewfees.setVisibility(View.INVISIBLE);
                }


                textViewname.setText(Name);
                textViewnametag.setText(Nametag);
                textViewaddress.setText(Location);
                textViewaddresstag.setText(Locationtag);
                textViewdistance.setText(Double.toString(Distance)+" Km ");
                textViewdistancetag.setText(Distancetag);

                if(Rating>=1){
         //           imageViewone.setBackground(ContextCompat.getDrawable(this,R.drawable.starfull));
                    imageViewone.setBackgroundResource(R.drawable.starfull);
                    Rating = Rating - 1.0;
                }
                else if(Rating==0.5){
         //           imageViewone.setBackground(ContextCompat.getDrawable(this,R.drawable.starhalf));
                    imageViewone.setBackgroundResource(R.drawable.starhalf);
                    Rating = Rating - 0.5;
                }

                if(Rating>=1){
//                    imageViewtwo.setBackground(ContextCompat.getDrawable(this,R.drawable.starfull));
                    imageViewtwo.setBackgroundResource(R.drawable.starfull);
                    Rating = Rating - 1.0;
                }
                else if(Rating==0.5){
//                    imageViewtwo.setBackground(ContextCompat.getDrawable(this,R.drawable.starhalf));
                    imageViewtwo.setBackgroundResource(R.drawable.starhalf);
                    Rating = Rating - 0.5;
                }

                if(Rating>=1){
//                    imageViewthree.setBackground(ContextCompat.getDrawable(this,R.drawable.starfull));
                    imageViewthree.setBackgroundResource(R.drawable.starfull);
                    Rating = Rating - 1.0;
                }
                else if(Rating==0.5){
//                    imageViewthree.setBackground(ContextCompat.getDrawable(this,R.drawable.starhalf));
                    imageViewthree.setBackgroundResource(R.drawable.starhalf);
                    Rating = Rating - 0.5;
                }

                if(Rating>=1){
//                    imageViewfour.setBackground(ContextCompat.getDrawable(this,R.drawable.starfull));
                    imageViewfour.setBackgroundResource(R.drawable.starfull);
                    Rating = Rating - 1.0;
                }
                else if(Rating==0.5){
//                    imageViewfour.setBackground(ContextCompat.getDrawable(this,R.drawable.starhalf));
                    imageViewfour.setBackgroundResource(R.drawable.starhalf);
                    Rating = Rating - 0.5;
                }

                if(Rating>=1){
//                    imageViewfive.setBackground(ContextCompat.getDrawable(this,R.drawable.starfull));
                    imageViewfive.setBackgroundResource(R.drawable.starfull);
                    Rating = Rating - 1.0;
                }
                else if(Rating==0.5){
//                    imageViewfive.setBackground(ContextCompat.getDrawable(this,R.drawable.starhalf));
                    imageViewfive.setBackgroundResource(R.drawable.starhalf);

                    Rating = Rating - 0.5;
                }




                String charone = Name.trim().substring(0,1);

                System.out.println("check here "+charone);

//                textViewImage.setText(charone);

                //String ImageUrl = "http://elasticbeanstalk-us-west-2-824932500732.s3.amazonaws.com/Images/"+image+".jpg";
                String ImageUrl = "https://cureissure.000webhostapp.com/"+image+".jpg";


                int viewHeightview=(int)(screenHeight*25)/100;
                int viewWidthview = (int)(screenWidth*35)/100
;                textViewImage.getLayoutParams().height=viewHeightview;
                textViewImage.getLayoutParams().width=viewWidthview;

                Glide.with(this).load(ImageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Drawable drawable = new BitmapDrawable(resource);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            textViewImage.setBackground(drawable);
                        }
                    }
                });

                tr.addView(item);

                tr.setBackgroundColor(Color.WHITE);
                tr.setTag(Id);
                tr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       DetailContent(view);
                    }
                });



                tablelayout.addView(tr,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            }
            catch (Exception e){

            }
            i++;
        }
    }
    public void DetailContent(View view){



        clicked_id = (String)view.getTag();

        Intent myIntent = new Intent(this, DetailContent.class);
        startActivity(myIntent);
    }

}
