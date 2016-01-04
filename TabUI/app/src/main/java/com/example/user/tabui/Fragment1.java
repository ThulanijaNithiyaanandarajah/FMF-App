package com.example.user.tabui;

/**
 * Created by USER on 12/30/2015.
 */


        import android.app.Activity;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.app.FragmentManager;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AutoCompleteTextView;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.NumberPicker;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;


public class Fragment1 extends Fragment {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */
    //Context context= getActivity().getApplicationContext();
    public static Fragment1 newInstance() {
        return new Fragment1();
    }
        String v1,val;
        AutoCompleteTextView from_1;
         AutoCompleteTextView to_1;
         AutoCompleteTextView autoText;
       // EditText to_1;
        EditText date_1;
        EditText date_2;
        EditText passengers;
        NumberPicker selBChld;
        Button  search_flight_submit;
    JSONObject jsonobject;
    JSONArray jsonarray;
    ArrayList<String> worldlist;
    AutoCompleteTextView autocompleteTextView;


    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment1, container, false);


     //   from_1=(EditText)view.findViewById(R.id.from_1_f1);

        date_1=(EditText)view.findViewById(R.id.date1_f1);
       // date_2=(EditText)view.findViewById(R.id.date2_f1);
        passengers = (EditText)view.findViewById(R.id.passengers_f1);
       // selBChld = (NumberPicker)v.findViewById(R.id.numberPicker2);
        search_flight_submit=(Button)view.findViewById(R.id.search_flight_submit_f1);
        from_1 = (AutoCompleteTextView)view.findViewById(R.id.from_1_f1);
        new DownloadJSON().execute();
        //from_1=autoText;
        to_1=(AutoCompleteTextView)view.findViewById(R.id.to_1_f1);
        new DownloadJSONForTo().execute();


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        date_1.setText(formattedDate.toString());


        search_flight_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String from, to, passeng;
                String date1, date2;
                int selAdt, selChld, day_1, month_1, year_1, day_2, month_2, year_2;

                from =  from_1.getText().toString();
                to = to_1.getText().toString();
                passeng = passengers.getText().toString();
                Toast.makeText(getContext(),from+ to,Toast.LENGTH_LONG).show();


            }
        });

        date_1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    // get_date(1);
                    Fragment1 f1=new Fragment1();
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getActivity().getFragmentManager(), "Date Picker");
                  //  Toast.makeText(getActivity().getApplicationContext(), getActivity().getFragmentManager().toString(), Toast.LENGTH_LONG).show();

                  //  DialogFragment newFragment = new NumberPickerFragment();
                   // newFragment.show(getActivity().getFragmentManager(), "Date Picker");
                }
            }

        });
/*        date_2.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
               //     get_date(2);
                }
            }

        }); */
        passengers.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //     show_passenger();
                }
            }

        });




        return view;

    }

    class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the WorldPopulation Class
            //    world = new ArrayList<WorldPopulation>();
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

            SearchableAdapter autoCompleteAdapter = new SearchableAdapter(getContext(), android.R.layout.simple_list_item_1, worldlist);
            from_1.setThreshold(1);
           from_1.setAdapter(autoCompleteAdapter);

        }
    }

    class DownloadJSONForTo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            // Locate the WorldPopulation Class
            //    world = new ArrayList<WorldPopulation>();
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

            SearchableAdapter autoCompleteAdapter = new SearchableAdapter(getContext(), android.R.layout.simple_list_item_1, worldlist);
            to_1.setThreshold(1);
            to_1.setAdapter(autoCompleteAdapter);

        }
    }

    public void show_passenger()
    {

        final Dialog d = new Dialog(getActivity().getApplicationContext());
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.add);
        // Button b2 = (Button) d.findViewById(R.id.button2);

        final NumberPicker adult = (NumberPicker) d.findViewById(R.id.adult);
        final NumberPicker child = (NumberPicker) d.findViewById(R.id.children);
        final NumberPicker infant = (NumberPicker) d.findViewById(R.id.infant);
        adult.setMaxValue(9); // max value 100
        child.setMaxValue(9); // max value 100
        infant.setMaxValue(9); // max value 100
        adult.setMinValue(1);   // min value 0
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
                passengers.setText(adultVal + " Adult " + childVal + " Children " + infantVal + " Infant");


                //Toast.makeText(MainActivity.this, v1, Toast.LENGTH_LONG).show();
            }
        });

        d.show();
    }
    public void get_date(final int type)
    {


        final String date1Val,date2Val ;

        final Dialog d = new Dialog(getActivity().getApplicationContext());
        if(type == 1){
            d.setTitle("Select Departure date");
        }else{
            d.setTitle("Select Arrival Date");
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

                    date_1.setText(day_1+"/"+month_1+"/"+year_1);

                }else{
                    day_2=date.getDayOfMonth();
                    month_2=date.getMonth()+1;
                    year_2=date.getYear();

                    date_2.setText(day_2+"/"+month_2+"/"+year_2);
                }



                //Toast.makeText(MainActivity.this, v1, Toast.LENGTH_LONG).show();
            }
        });

        d.show();
    }


}