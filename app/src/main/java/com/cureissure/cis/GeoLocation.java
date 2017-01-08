package com.cureissure.cis;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by RAJAT SINGH on 11/21/2016.
 */

public class GeoLocation extends AppCompatActivity implements LocationListener {

    private  Context mContext;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    public static String Address_Global = null;
    public static double Longitude_user ;
    public static double Latititude_user;
    // Flag for GPS status
    boolean isGPSEnabled = false;

    // Flag for network status
    boolean isNetworkEnabled = false;

    // Flag for GPS status
    boolean canGetLocation = false;

    Location location; // Location
    double latitude; // Latitude
    double longitude; // Longitude

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public void GPSTracker(Context context,LocationManager locationManager) {
        this.mContext = context;



        getLocation(locationManager);
    }

    public Location getLocation(LocationManager locationManager) {
        try {


            // Getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // Getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // No network provider is enabled
                System.out.println("gsp one one one");

            } else {

                System.out.println("gsp one one one one");

                this.canGetLocation = true;
                if (isNetworkEnabled) {

                    System.out.println("gsp one one two");

                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        System.out.println("gsp one one");
                        if (location != null) {
                            System.out.println("gsp one two");

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                            System.out.println("Logitude and Latitude are "+latitude+" "+longitude);

                        }
                    }
                }
                // If GPS enabled, get latitude/longitude using GPS Services
               else if (isGPSEnabled) {
                    System.out.println("gsp one one three");

                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            System.out.println("gsp one three");
                            if (location != null) {
                                System.out.println("gsp one four");
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                System.out.println("Logitude and Latitude are "+latitude+" "+longitude);

                            }
                        }
                    }
                }
            }

            if(location==null){
                MainActivity.location_loading_alertDialog.dismiss();

                MainActivity.location_loading_builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                MainActivity.location_loading_builder.setMessage("Not Able to load location , please change location from menu option");
                MainActivity.location_loading_alertDialog =  MainActivity.location_loading_builder.create();

                MainActivity.location_loading_alertDialog.show();
               // wifigps.checkGPSLocation(MainActivity.locationManager,connectivityManager,alertDialogBuilder);
            }
else {

                Geocoder geocoder = new Geocoder(this);
                List<Address> addr = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                Longitude_user = location.getLongitude();
                Latititude_user = location.getLatitude();

                Address_Global = "";
                if (addr.get(0).getAddressLine(0) != null)
                    Address_Global += addr.get(0).getAddressLine(0);

                if (addr.get(0).getAddressLine(1) != null)
                    Address_Global += addr.get(0).getAddressLine(1);

                if (addr.get(0).getAddressLine(2) != null)
                    Address_Global += addr.get(0).getAddressLine(2);

                if (addr.get(0).getAddressLine(3) != null)
                    Address_Global += addr.get(0).getAddressLine(3);

                //  Address_Global = addr.get(0).getAddressLine(0)+" "+addr.get(0).getAddressLine(1)+" "+addr.get(0).getAddressLine(2)+" "+addr.get(0).getAddressLine(3);

                //  Toast.makeText(mContext,addr.get(0).getAddressLine(0)+" "+addr.get(0).getAddressLine(1)+" "+addr.get(0).getAddressLine(2)+" "+addr.get(0).getAddressLine(3), Toast.LENGTH_LONG).show();

                MainActivity.location_loading_alertDialog.dismiss();

                android.support.v7.app.AlertDialog.Builder builder;

                builder = new android.support.v7.app.AlertDialog.Builder(mContext);
                builder.setTitle("New Location");
                builder.setMessage(Address_Global);
                builder.setPositiveButton(android.R.string.ok, null);

                builder.show();

                System.out.println(addr.get(0));
                APIsCall.detailDoctorAPI();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }


    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app.
     * */


    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }


    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/Wi-Fi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }


    /**
     * Function to show settings alert dialog.
     * On pressing the Settings button it will launch Settings Options.
     * */
   /*
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
*/

    @Override
    public void onLocationChanged(Location location) {
    }


    @Override
    public void onProviderDisabled(String provider) {
    }


    @Override
    public void onProviderEnabled(String provider) {
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

/*
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }*/
}
