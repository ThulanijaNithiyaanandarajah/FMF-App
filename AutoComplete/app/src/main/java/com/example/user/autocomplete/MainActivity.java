package com.example.user.autocomplete;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    JSONObject jsonobject;
    JSONArray jsonarray;
    ProgressDialog mProgressDialog;
    ArrayList<String> worldlist;
    ArrayList<WorldPopulation> world;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadJSON().execute();

/*
       String[] autoCompleteOptions = new String[]{"Hello", "Hey", "Heja", "Hi", "Hola", "Bonjour", "Gday", "Goodbye", "Sayonara", "Farewell", "Adios"};
        ArrayAdapter autoCompleteAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, autoCompleteOptions);
        AutoCompleteTextView autocompleteTextView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteInput);
        autocompleteTextView.setThreshold(1);
        autocompleteTextView.setAdapter(autoCompleteAdapter);
*/

    }
    // Download JSON file AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the WorldPopulation Class
            world = new ArrayList<WorldPopulation>();
            // Create an array to populate the spinner
            worldlist = new ArrayList<String>();
            // JSON file URL address
            jsonarray = JSONFunctions
                    .getJSONfromURL("https://www.findmyfare.com/citiezz.json");
        //    Toast.makeText(getApplicationContext(),jsonobject.toString(), Toast.LENGTH_SHORT).show();
            try {
                // Locate the NodeList name
               jsonobject = jsonarray.getJSONObject(0);
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                     worldlist.add(jsonobject.getString("label"));

                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            ArrayAdapter autoCompleteAdapter = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_dropdown_item,worldlist);
            AutoCompleteTextView autocompleteTextView = (AutoCompleteTextView) findViewById(R.id.AutoCompleteInput);
            autocompleteTextView.setThreshold(0);
            autocompleteTextView.setAdapter(autoCompleteAdapter);



        }
    }


}
