package com.example.user.fmfapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText from_text;
    EditText to_text;
    Button search_flight_submit;

    String v1,val;
    EditText from;
    EditText to;
    EditText dd1;
    EditText rtd1;
    EditText passengers1;
   // NumberPicker selBChld;



  //  final String from_1 = "Colombo, Sri Lanka (CMB)";
  //  final String to_1 = "London, UK - Heathrow Airport (LHR)";
  //  final String dd = "11/01/2016";
  //  final String rtd = "14/01/2016";
    final String hdn_flt_type="RT";
     String selBChld ="0";
      String selBInf="0";
     String selBAdt="1";
    final String selBCos="y";
    final String airline="";
    final String multicity_count ="2";

    String url = "https://ibe.findmyfare.lk/search_flight2.php";
    //String url2 ="http://192.168.56.1/fmf/bookingDetails.php";
    private static final String TAG = "MainActivity.java";

    String from_1;
    String to_1;
    String dd;
    String rtd;
    String passengers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight);

        from=(EditText)findViewById(R.id.from_1);

        to=(EditText)findViewById(R.id.to_1);

        dd1=(EditText)findViewById(R.id.date1);

        rtd1=(EditText)findViewById(R.id.date2);

        passengers1 = (EditText)findViewById(R.id.passengers);


        search_flight_submit=(Button)findViewById(R.id.search_flight_submit);



        search_flight_submit.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        to_1=to.getText().toString();
                        from_1 = from.getText().toString();
                        dd=dd1.getText().toString();
                        rtd=rtd1.getText().toString();
                        passengers=passengers1.getText().toString();
                        String[] separated = passengers.split(" ");
                        selBAdt=separated[0];
                        selBChld=separated[2];
                        selBInf=separated[4];

                        CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {

                                    JSONObject sendResult = new JSONObject((response.toString()));
                                    Log.d("msg", sendResult.toString());
                                    Toast.makeText(getApplication(),sendResult.toString(), Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("from_1", from_1.toString());
                                params.put("to_1", to_1.toString());
                                params.put("dd", dd.toString());
                                params.put("rtd", rtd.toString());
                                params.put(" hdn_flt_type", hdn_flt_type);
                                params.put("selBChld", selBChld);
                                params.put("selBAdt", selBAdt);
                                params.put(" selBInf", selBInf);
                                params.put("airline", airline);
                                params.put("multicity_count", multicity_count);
                                params.put("selBCos", selBCos);
                                params.put("mapp","1");
                                return params;
                            }

                        };


                        //jsObjRequest.setShouldCache(false);
                        MyVolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsObjRequest);

                    }
                });


        dd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    get_date(1);
                }
            }

        });
        rtd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    get_date(2);
                }
            }

        });
        passengers1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    show_passenger();
                }
            }

        });
    }



    public void show_passenger()
    {

        final Dialog d = new Dialog(MainActivity.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.add);
        // Button b2 = (Button) d.findViewById(R.id.button2);

        final NumberPicker adult = (NumberPicker) d.findViewById(R.id.adult);
        final NumberPicker child = (NumberPicker) d.findViewById(R.id.children);
        final NumberPicker infant = (NumberPicker) d.findViewById(R.id.infant);
        adult.setMaxValue(100); // max value 100
        child.setMaxValue(100); // max value 100
        infant.setMaxValue(100); // max value 100
        adult.setMinValue(0);   // min value 0
        child.setMinValue(0);   // min value 0
        infant.setMinValue(0);   // min value 0
        adult.setWrapSelectorWheel(false);
        child.setWrapSelectorWheel(false);
        infant.setWrapSelectorWheel(false);
        //    np.setOnValueChangedListener(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adultVal, childVal, infantVal;
                adultVal = String.valueOf(adult.getValue());
                childVal = String.valueOf(child.getValue());
                infantVal = String.valueOf(infant.getValue());
                d.dismiss();
                passengers1.setText(adultVal + " Adult " + childVal + " Children " + infantVal + " Infant");



            }
        });


        d.show();



    }
    public void get_date(final int type)
    {


        final String date1Val,date2Val ;

        final Dialog d = new Dialog(MainActivity.this);
        if(type == 1){
            d.setTitle("Select Depart date");
        }else{
            d.setTitle("Select Return Date");
        }

        d.setContentView(R.layout.select_date_popup);
        Button b1 = (Button) d.findViewById(R.id.add_date);
        // Button b2 = (Button) d.findViewById(R.id.button2);

        final DatePicker date = (DatePicker) d.findViewById(R.id.date);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day_1,month_1,year_1,day_2,month_2,year_2;
                d.dismiss();
                if(type == 1){
                    day_1=date.getDayOfMonth();
                    month_1=date.getMonth()+1;
                    year_1=date.getYear();

                    dd1.setText(day_1+"/"+month_1+"/"+year_1);

                }else{
                    day_2=date.getDayOfMonth();
                    month_2=date.getMonth()+1;
                    year_2=date.getYear();

                    rtd1.setText(day_2 + "/" + month_2 + "/" + year_2);
                }




            }
        });



        d.show();



    }

}
