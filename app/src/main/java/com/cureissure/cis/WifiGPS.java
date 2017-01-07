package com.cureissure.cis;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import com.cureissure.cis.GeoLocation;

import android.view.View;
import android.widget.Toast;

/**
 * Created by RAJAT SINGH on 11/20/2016.
 */

public class WifiGPS extends Activity{

    public static String pincode ;
    public static boolean gps_access_granted = false;

    public static MainActivity ViewForGPSWiFi;

     void  checkGPSLocation(LocationManager locationManager,ConnectivityManager connectivityManager,AlertDialog.Builder alertDialogBuilder){
System.out.println("gps come ");
        GeoLocation.Address_Global = null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            System.out.println("gps come come");
          //  Toast.makeText(ViewForGPSWiFi, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
            checkWIFIConnection(    locationManager,connectivityManager,alertDialogBuilder);
        }else{

         //   System.out.println("Resume called also");
            System.out.println("gps come come oome");
            showGPSDisabledAlertToUser(alertDialogBuilder);

        }

    }

    private void showGPSDisabledAlertToUser(AlertDialog.Builder alertDialogBuilder){




        alertDialogBuilder.setMessage("GPS is disabled in your device , Please Enable")
                .setCancelable(false);

        alertDialogBuilder.setPositiveButton("GPS SETTING",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                ViewForGPSWiFi.startActivity(callGPSSettingIntent);
                              //  ViewForGPSWiFi.finish();
                            }
                        });
       /* alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });*/
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void checkWIFIConnection(LocationManager locationManager,ConnectivityManager connectivityManager,AlertDialog.Builder alertDialogBuilder){
        if (!isNetworkAvailable(connectivityManager)) {

         //   Toast.makeText(ViewForGPSWiFi, "No Internet Connection", Toast.LENGTH_SHORT).show();

            alertDialogBuilder.setMessage("No Internet Connection, Please Enable ")
                    .setCancelable(false);

            alertDialogBuilder.setPositiveButton("MOBILE DATA",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){
//
//                                    Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
//                                    ViewForGPSWiFi.startActivity(intent);
                                    Intent intent = new Intent();
                                    intent.setComponent(new ComponentName("com.android.settings","com.android.settings.Settings$DataUsageSummaryActivity"));
                                    ViewForGPSWiFi.startActivity(intent);

                                }
                            });
            alertDialogBuilder.setNegativeButton("WIFI SETTING",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){

                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            ViewForGPSWiFi.startActivity(intent);

                        }
                    });
       /*     alertDialogBuilder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            dialog.cancel();
                        }
                    });*/
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();


          //  Toast.makeText(ViewForGPSWiFi, "No Internet Connection", Toast.LENGTH_LONG).show();


        }

        else {


            GeoLocation geoLocation  = new GeoLocation();
            geoLocation.GPSTracker(ViewForGPSWiFi,locationManager);
            System.out.println("gps checking here ");


        }
    }

    public boolean isNetworkAvailable(ConnectivityManager connectivityManager) {

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
