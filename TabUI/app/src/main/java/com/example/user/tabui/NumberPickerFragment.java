package com.example.user.tabui;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class NumberPickerFragment extends DialogFragment  {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View numberView = inflater.inflate(R.layout.dialog, container, false);

        getDialog().setTitle("Select");
        Button b1 = (Button) numberView.findViewById(R.id.add);
        final NumberPicker adult = (NumberPicker) numberView.findViewById(R.id.adult);
        final NumberPicker child = (NumberPicker) numberView.findViewById(R.id.children);
        final NumberPicker infant = (NumberPicker) numberView.findViewById(R.id.infant);
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


        View mainAct = getActivity().getLayoutInflater().inflate(R.layout.fragment_fragment1, null);
        View MainView = inflater.inflate(R.layout.fragment_fragment1, container, true);
        final EditText passengers=(EditText)getActivity().findViewById(R.id.passengers_f1);

        //Toast.makeText(getActivity(),  String.valueOf(adult.getValue()), Toast.LENGTH_LONG).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(),  String.valueOf(adult.getValue()), Toast.LENGTH_LONG).show();
                String adultVal, childVal, infantVal;
                adultVal = String.valueOf(adult.getValue());
                childVal = String.valueOf(child.getValue());
                infantVal = String.valueOf(infant.getValue());
                Log.v("abc", "2sd");
                passengers.setText(adultVal + " Adult |" + childVal + " Children |" + infantVal + " Infant");


                getDialog().dismiss();
            }
        });


        return numberView;
    }








}
