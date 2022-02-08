package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class CalendarActivity extends AppCompatActivity {

    private DatePicker datePicker;

    private Button bt_calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initView();

        initAction();

    }

    public void initView(){
        datePicker = findViewById(R.id.calendar_DatePicker);
        bt_calendar = findViewById(R.id.calendar_bt);
    }

    public void initAction(){
        bt_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        StringBuffer target_date_time = new StringBuffer();
                        target_date_time.append(year+"-"+month+"-"+dayOfMonth);
                        Log.e("对话框", "year = " + year + ", month = " + month+", day = "+dayOfMonth);
                        TimePickerDialog timePicker = new TimePickerDialog(CalendarActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Log.e("对话框", "hourOfDay = " + hourOfDay + ", minute = " + minute);
                                target_date_time.append(" "+hourOfDay+":00:00");
                                Log.e("最终时间：：", String.valueOf(target_date_time));
                            }

                        }, 0, 0, true);

                        timePicker.show();

                    }
                },2022,1,13);

                datePickerDialog.show();


//                TimePickerDialog timePicker = new TimePickerDialog(CalendarActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        Log.e("对话框", "hourOfDay = " + hourOfDay + ", minute = " + minute);
//
//                    }
//
//                }, 0, 0, true);
//
//                timePicker.show();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
//        Log.e("::onStart::","");
        System.out.println(getClass().toString()+"::onStart::");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.e("::onPause::","");
        System.out.println(getClass().toString()+"::onPause::");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.e("::onResume::","");
        System.out.println(getClass().toString()+"::onResume::");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.e("::onStop::","");
        System.out.println(getClass().toString()+"::onStop::");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.e("::onDestroy::","");
        System.out.println(getClass().toString()+"::onDestroy::");
//        if(bcReceiver!=null){
//            unregisterReceiver(bcReceiver);
//        }

    }

}