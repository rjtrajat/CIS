package com.cureissure.cis;

import android.content.Context;
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


    public static Context context = null;


    public static void detailDoctorAPI() {

        RequestParams rp = new RequestParams();
        rp.put("Lon", GeoLocation.Longitude_user);
        rp.put("Lat", GeoLocation.Latititude_user);
//        rp.put("Lon", 77.88);
//        rp.put("Lat", 88.12);

        System.out.println("Response is lon "+GeoLocation.Longitude_user+" "+GeoLocation.Latititude_user);

        HttpUtils.get("detailDoctor", rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                System.out.println("Response is array " + response);

            MainActivity mainActivity = (MainActivity) context;
                mainActivity.TableDesign(response,"Doctors");

            }

        });


    }

    public static void detailHospitalAPI() {

        RequestParams rp = new RequestParams();
        rp.put("Lon", GeoLocation.Longitude_user);
        rp.put("Lat", GeoLocation.Latititude_user);

        HttpUtils.get("detailHospital", rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.TableDesign(response,"Hospitals");

            }

        });


    }

    public static void detailTestCenterAPI() {
        RequestParams rp = new RequestParams();
        rp.put("Lon", GeoLocation.Longitude_user);
        rp.put("Lat", GeoLocation.Latititude_user);


        HttpUtils.get("detailTestCenter", rp, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.TableDesign(response,"TestCenters");

            }

        });

    }

    public static void detailMedicalShopAPI() {

        RequestParams rp = new RequestParams();
        rp.put("Lon", GeoLocation.Longitude_user);
        rp.put("Lat", GeoLocation.Latititude_user);


        HttpUtils.get("detailMedicalShop", rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Pull out the first event on the public timeline
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.TableDesign(response,"MedicalShops");

            }
        });
    }

    public static void detailScheduleAPI(String Id) {

        System.out.println("Response is unique "+Id);

        RequestParams rp = new RequestParams();

        rp.put("unique_key_appointment", Id);


        HttpUtils.get("detailSchedule", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    AppointmentDetail  appointmentDetail = (AppointmentDetail) context;
                    appointmentDetail.detailDesign(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                AppointmentDetail  appointmentDetail = (AppointmentDetail) context;
                appointmentDetail.notFoundId();

            }
        });

    }

    public static void detailIndividualDoctorAPI(String Id) {


        RequestParams rp = new RequestParams();

        rp.put("cis_doc_id", Id);


        HttpUtils.get("detailIndividualDoctor", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    DetailContent detailContent = (DetailContent) context;
                    detailContent.DetailDesign(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });


    }

    public static void detailIndividualHospitalAPI(String Id) {

        RequestParams rp = new RequestParams();

        rp.put("cis_hos_id", Id);


        HttpUtils.get("detailIndividualHospital", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    DetailContent detailContent = (DetailContent) context;
                    detailContent.DetailDesign(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });
    }


    public static void detailIndividualTestCenterAPI(String Id) {

        RequestParams rp = new RequestParams();

        rp.put("cis_test_id", Id);


        HttpUtils.get("detailIndividualTestCenter", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    DetailContent detailContent = (DetailContent) context;
                    detailContent.DetailDesign(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });
    }

    public static void detailIndividualMedicalShopAPI(String Id) {
        RequestParams rp = new RequestParams();

        rp.put("cis_med_id", Id);


        HttpUtils.get("detailIndividualMedicalShop", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    DetailContent detailContent = (DetailContent) context;
                    detailContent.DetailDesign(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });
    }

    public static void detailIndividualDoctorAppointAPI(String Id) {


        RequestParams rp = new RequestParams();

        rp.put("cis_doc_id", Id);


        HttpUtils.get("detailIndividualDoctorAppoint", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    AppointmentDetail appointmentDetail = (AppointmentDetail) context;
                    appointmentDetail.updateDoctorAppointed(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });


    }

    public static void detailIndividualHospitalAppointAPI(String Id) {

        RequestParams rp = new RequestParams();

        rp.put("cis_hos_id", Id);


        HttpUtils.get("detailIndividualHospitalAppoint", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    AppointmentDetail appointmentDetail = (AppointmentDetail) context;
                    appointmentDetail.updateHospitalAppointed(response);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });
    }

    public static void getLatestUniquePatientKey(){

        RequestParams rp = new RequestParams();




        HttpUtils.get("latestUniquePatientKey", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    Schedule_page schedule_page = (Schedule_page) context;
                    schedule_page.schedule_update(response);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });
    }

    public static void setPatientDetailentry(String uniquekeyappointment,String nameofpatient,String contactofpatient, String mailidofpatient,Double longitudeofpatient,Double latitudeofpatient,String problemdescriptionofpatient,String fulladdressofpatient,String statusvalue,String statusdatetime, String dateofappointment, String timeofappointment,Boolean paid,String appointmenttype,String appointmenttypekey){
        RequestParams rp = new RequestParams();
        rp.put("uniquekeyappointment", uniquekeyappointment);
        rp.put("nameofpatient", nameofpatient);
        rp.put("contactofpatient", contactofpatient);
        rp.put("mailidofpatient", mailidofpatient);
        rp.put("longitudeofpatient", longitudeofpatient);
        rp.put("latitudeofpatient", latitudeofpatient);
        rp.put("problemdescriptionofpatient", problemdescriptionofpatient);
        rp.put("fulladdressofpatient", fulladdressofpatient);
        rp.put("statusvalue", statusvalue);
        rp.put("statusdatetime", statusdatetime);
        rp.put("dateofappointment", dateofappointment);
        rp.put("timeofappointment", timeofappointment);
        rp.put("paid", paid);
        rp.put("appointmenttype", appointmenttype);
        rp.put("appointmenttypekey", appointmenttypekey);

        HttpUtils.get("patientDetailentry", rp, new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                System.out.println("Response is json detail " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    Schedule_page schedule_page = (Schedule_page) context;
                    schedule_page.goToMain();



                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



        });

    }

}
