package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    private Button bt_5;
    private Button bt_6;
    private Button bt_7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;


        System.out.println("高度："+height);
        System.out.println("宽度："+width);

        initView();
        initAction();
    }

    public void initView(){

        bt_1 = findViewById(R.id.menu_bt_01);
        bt_2 = findViewById(R.id.menu_bt_02);
        bt_3 = findViewById(R.id.menu_bt_03);
        bt_4 = findViewById(R.id.menu_bt_04);
        bt_5 = findViewById(R.id.menu_bt_05);
        bt_6 = findViewById(R.id.menu_bt_06);
        bt_7 = findViewById(R.id.menu_bt_07);

    }

    public void initAction(){

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,TimeActivity.class);
                startActivity(intent);


            }
        });

        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,EchartActivity.class);
                startActivity(intent);


            }
        });

        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,MapActivity.class);
                startActivity(intent);


            }
        });

        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,MpchartActivity.class);
                startActivity(intent);


            }
        });

        bt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,horizonViewActivity.class);
                startActivity(intent);
            }
        });

        bt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });

        bt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,SurfaceviewActivity.class);
                startActivity(intent);
            }
        });

    }
}