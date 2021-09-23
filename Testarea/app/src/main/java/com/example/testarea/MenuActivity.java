package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private Button bt_1;
    private Button bt_2;
    private Button bt_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initView();
        initAction();
    }

    public void initView(){

        bt_1 = findViewById(R.id.menu_bt_01);
        bt_2 = findViewById(R.id.menu_bt_02);
        bt_3 = findViewById(R.id.menu_bt_03);

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

    }
}