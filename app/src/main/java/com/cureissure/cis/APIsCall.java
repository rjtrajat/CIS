package com.cureissure.cis;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by RAJAT SINGH on 12/11/2016.
 */

public class APIsCall {
    public static void detailDoctorAPI(){

        RequestParams rp = new RequestParams();
        rp.put("Lon",GeoLocation.Longitude_user);
        rp.put("Lat",GeoLocation.Latititude_user);


        HttpUtils.get("detailDoctor", rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response){
                System.out.println("Response is "+response);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is "+response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                System.out.println("Response is timeline "+timeline);

            }

        });


    }
    public static void detailHospitalAPI(){

        RequestParams rp = new RequestParams();
        rp.put("Lon",GeoLocation.Longitude_user);
        rp.put("Lat",GeoLocation.Latititude_user);

        HttpUtils.get("detailHospital", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response){
                System.out.println("Response is "+response);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is "+response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                System.out.println("Response is timeline "+timeline);

            }

        });


    }
    public static void detailTestCenterAPI(){
        RequestParams rp = new RequestParams();
        rp.put("Lon",GeoLocation.Longitude_user);
        rp.put("Lat",GeoLocation.Latititude_user);


        HttpUtils.get("detailTestCenter", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                System.out.println("Response is timeline "+timeline);

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is "+response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


        });

    }
    public static void detailMedicalShopAPI(){

        RequestParams rp = new RequestParams();
        rp.put("Lon",GeoLocation.Longitude_user);
        rp.put("Lat",GeoLocation.Latititude_user);


        HttpUtils.get("detailMedicalShop", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is "+response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                System.out.println("Response is timeline "+timeline);

            }
        });
    }
    public static void detailScheduleAPI(){

    }
    public static void detailIndividualDoctorAPI(){

    }
    public static void detailIndividualHospitalAPI(){

    }
    public static void detailIndividualTestCenterAPI(){

    }
    public static void detailIndividualMedicalShopAPI(){

    }
}
