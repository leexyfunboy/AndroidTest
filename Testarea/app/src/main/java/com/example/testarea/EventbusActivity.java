package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.testarea.entity.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventbusActivity extends AppCompatActivity {

    private Button bt_01;
    private Button bt_02;
    private Button bt_03;
    private Button bt_04;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);

        initView();
        initAction();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 5)
    public void OnReceiveMsg(EventMessage message){
        Log.e("EventAcRc:Thread",Thread.currentThread().getName());
        Log.e("EventAcRc", "onReceiveMsg: " + message.toString());
    }

    public void initView(){
        bt_01 = findViewById(R.id.Eventbus_bt_01);
        bt_02 = findViewById(R.id.Eventbus_bt_02);
        bt_03 = findViewById(R.id.Eventbus_bt_03);
        bt_04 = findViewById(R.id.Eventbus_bt_04);
    }

    public void initAction(){
        bt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventMessage msg = new EventMessage(1,"hello event");
                Log.e("EventAc:Thread",Thread.currentThread().getName());
                EventBus.getDefault().post(msg);
            }
        });

        bt_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testEventBus_newThread();
            }
        });

        bt_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventMessage msg = new EventMessage(1,"hello event");
                Log.e("EventAc:Thread",Thread.currentThread().getName());
                EventBus.getDefault().postSticky(msg);
            }
        });

        bt_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventbusActivity.this,TestEventBus_Sticky.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 新建一个新的线程，用于测试EventBus中订阅者的ThreadMode.ASYNC
     */
    public void testEventBus_newThread(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Log.e("EventAc_newThread::", Thread.currentThread().getName());
                        EventMessage msg = new EventMessage(1,"hello eventbus");
                        EventBus.getDefault().post(msg);
                    }
                }
        ).start();
    }
}