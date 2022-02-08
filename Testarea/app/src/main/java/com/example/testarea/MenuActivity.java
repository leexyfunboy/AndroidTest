package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testarea.entity.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MenuActivity extends AppCompatActivity {

    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    private Button bt_5;
    private Button bt_6;
    private Button bt_7;
    private Button bt_8;
    private Button bt_9;
    private Button bt_10;
    private Button bt_11;
    private Button bt_12;
    private Button bt_13;
    private Button bt_14;
    private Button bt_15;
    private Button bt_16;
    private Button bt_17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Log.d("MenuAc","onCreate()");
        createNotificationChannel();
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MenuAc","onStart()");
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MenuAc","onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MenuAc","onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MenuAc","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MenuAc","onDestroy()");
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING,priority = 1)
    public void onReceive2(EventMessage message) {
        Log.e("MenuAc:POSTING",Thread.currentThread().getName());
//        Log.e("MenuAc_2", "onReceiveMsg: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 3)
    public void onReceiveMsg(EventMessage message) {
        Log.e("MenuAc:MAIN",Thread.currentThread().getName());
//        Log.e("MenuAc", "onReceiveMsg: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND,priority = 4)
    public void onReceive3(EventMessage message) {
        Log.e("MenuAc:BACKGROUND",Thread.currentThread().getName());
//        Log.e("MenuAc_3", "onReceiveMsg: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC,priority = 5)
    public void onReceive4(EventMessage message) {
        Log.e("MenuAc:ASYNC",Thread.currentThread().getName());
//        Log.e("MenuAc_4", "onReceiveMsg: " + message.toString());
    }

    /**
     * 为Notification做准备
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "leexy";
            String description = "for test";
            //不同的重要程度会影响通知显示的方式
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("110", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void initView(){
        bt_1 = findViewById(R.id.menu_bt_01);
        bt_2 = findViewById(R.id.menu_bt_02);
        bt_3 = findViewById(R.id.menu_bt_03);
        bt_4 = findViewById(R.id.menu_bt_04);
        bt_5 = findViewById(R.id.menu_bt_05);
        bt_6 = findViewById(R.id.menu_bt_06);
        bt_7 = findViewById(R.id.menu_bt_07);
        bt_8 = findViewById(R.id.menu_bt_08);
        bt_9 = findViewById(R.id.menu_bt_09);
        bt_10 = findViewById(R.id.menu_bt_10);
        bt_11 = findViewById(R.id.menu_bt_11);
        bt_12 = findViewById(R.id.menu_bt_12);
        bt_13 = findViewById(R.id.menu_bt_13);
        bt_14 = findViewById(R.id.menu_bt_14);
        bt_15 = findViewById(R.id.menu_bt_15);
        bt_16 = findViewById(R.id.menu_bt_16);
        bt_17 = findViewById(R.id.menu_bt_17);
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

        bt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,PermissionActivity.class);
                startActivity(intent);
            }
        });

        bt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,BlueToothActivity.class);
                startActivity(intent);
            }
        });

        bt_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,GpslocationActivity.class);
                startActivity(intent);
            }
        });

        bt_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,SaveIdentityActivity.class);
                startActivity(intent);
            }
        });

        bt_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,BroadcastreceiverActivity.class);
                startActivity(intent);
            }
        });

        bt_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,TcpTest.class);
                startActivity(intent);
            }
        });

        bt_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,TakePhoto.class);
                startActivity(intent);
            }
        });

        bt_15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,Bluetooth2.class);
                startActivity(intent);
            }
        });

        bt_16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,NotificationTest.class);
                startActivity(intent);
            }
        });

        bt_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,EventbusActivity.class);
                startActivity(intent);
            }
        });
    }
}