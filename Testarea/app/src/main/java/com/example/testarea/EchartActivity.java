package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.testarea.Utils.BCReceiver;

public class EchartActivity extends AppCompatActivity {

    BCReceiver bcReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echart);

        IntentFilter intentFilter=new IntentFilter();
        //系统网络状态变化时，系统发出的就是android.net.conn.CONNECTIVITY_CHANGE这条广播
        intentFilter.addAction("com.example.testarea.Utils.BCReceiver");
//        networkChangeReceiver=new NetworkChangeReceiver();
        //注册广播
        bcReceiver = new BCReceiver();
        registerReceiver(bcReceiver,intentFilter);
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