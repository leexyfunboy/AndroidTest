package com.example.testarea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.testarea.databinding.ActivityWebviewBinding;
import com.example.testarea.services.MyService;

public class WebviewAC extends AppCompatActivity {

    private ActivityWebviewBinding binding;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initAction();
        //binding.webViewACWv.loadUrl("https://www.baidu.com/");


        Intent intent = new Intent(this, MyService.class);
//        startService(intent);
//        startService(intent);
//        stopService(intent);


        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.LocalBinder binder = (MyService.LocalBinder)service;
                myService = binder.getService();
                System.out.println("getCount:: "+myService.getCount());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        bindService(intent,conn,BIND_AUTO_CREATE);




    }

    public void initAction(){

    }


}