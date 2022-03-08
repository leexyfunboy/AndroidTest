package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
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

import com.example.testarea.databinding.ActivityMenuBinding;
import com.example.testarea.entity.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xcrash.XCrash;

public class MenuActivity extends AppCompatActivity {

//    private Button bt_1;
//    private Button bt_2;
//    private Button bt_3;
//    private Button bt_4;
//    private Button bt_5;
//    private Button bt_6;
//    private Button bt_7;
//    private Button bt_8;
//    private Button bt_9;
//    private Button bt_10;
//    private Button bt_11;
//    private Button bt_12;
//    private Button bt_13;
//    private Button bt_14;
//    private Button bt_15;
//    private Button bt_16;
//    private Button bt_17;
//    private Button bt_18;
//    private Button bt_19;



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        XCrash.init(this);      //初始化Xcrash
    }

    private ActivityMenuBinding bonding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bonding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = bonding.getRoot();
        setContentView(view);
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

//        initView();
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
    protected void onRestart() {
        super.onRestart();
        Log.d("MenuAc","onReStart()");
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

//    public void initView(){
//        bt_1 = findViewById(R.id.menu_bt_01);
//        bt_2 = findViewById(R.id.menu_bt_02);
//        bt_3 = findViewById(R.id.menu_bt_03);
//        bt_4 = findViewById(R.id.menu_bt_04);
//        bt_5 = findViewById(R.id.menu_bt_05);
//        bt_6 = findViewById(R.id.menu_bt_06);
//        bt_7 = findViewById(R.id.menu_bt_07);
//        bt_8 = findViewById(R.id.menu_bt_08);
//        bt_9 = findViewById(R.id.menu_bt_09);
//        bt_10 = findViewById(R.id.menu_bt_10);
//        bt_11 = findViewById(R.id.menu_bt_11);
//        bt_12 = findViewById(R.id.menu_bt_12);
//        bt_13 = findViewById(R.id.menu_bt_13);
//        bt_14 = findViewById(R.id.menu_bt_14);
//        bt_15 = findViewById(R.id.menu_bt_15);
//        bt_16 = findViewById(R.id.menu_bt_16);
//        bt_17 = findViewById(R.id.menu_bt_17);
//        bt_18 = findViewById(R.id.menu_bt_18);
//        bt_19 = findViewById(R.id.menu_bt_19);
//    }

    public void initAction(){

        bonding.menuBtNetWorkPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,NetworkpartAC.class);
                startActivity(intent);
            }
        });

        bonding.menuBtService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ServiceAC.class);
                startActivity(intent);
            }
        });

        bonding.menuBtWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,WebviewAC.class);
                startActivity(intent);
            }
        });

        bonding.menuBt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,TimeActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,EchartActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,MpchartActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,horizonViewActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,CalendarActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,SurfaceviewActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,PermissionActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,BlueToothActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,GpslocationActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,SaveIdentityActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,BroadcastreceiverActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,TcpTest.class);
                startActivity(intent);
            }
        });

        bonding.menuBt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,TakePhoto.class);
                startActivity(intent);
            }
        });

        bonding.menuBt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,Bluetooth2.class);
                startActivity(intent);
            }
        });

        bonding.menuBt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,NotificationTest.class);
                startActivity(intent);
            }
        });

        bonding.menuBt17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,EventbusActivity.class);
                startActivity(intent);
            }
        });

        bonding.menuBt18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,AlertdialogAC.class);
                startActivity(intent);
            }
        });

        bonding.menuBt19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ProgressBarAC.class);
                startActivity(intent);
            }
        });

        bonding.menuBt20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,Retrofit_Test.class);
                startActivity(intent);
            }
        });
    }
}