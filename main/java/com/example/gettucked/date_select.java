package com.example.gettucked;

import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class date_select extends AppCompatActivity {

    private static final String TAG = "hotels";

    private TextView displayDate;
    private static DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_date_select);

        displayDate = findViewById(R.id.textVdate);


        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(date_select.this, android.R.style.Theme_Holo_Panel, onDateSetListener, year, month, day);

                //compiler error but still runs for me - Mike

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };
    }
}