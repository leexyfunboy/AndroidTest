package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testarea.Utils.BCReceiver;

public class BroadcastreceiverActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    TestReceiver testReceiver;
    BCReceiver bcReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);

        initView();
        initAction();
//        IntentFilter intentFilter=new IntentFilter();
//        //系统网络状态变化时，系统发出的就是android.net.conn.CONNECTIVITY_CHANGE这条广播
//        intentFilter.addAction("com.example.testarea.Utils.BCReceiver");
////        networkChangeReceiver=new NetworkChangeReceiver();
//        //注册广播
//        bcReceiver = new BCReceiver();
//        registerReceiver(bcReceiver,intentFilter);
        IntentFilter filter = new IntentFilter();
        filter.setPriority(100);
        filter.addAction("com.example.testarea.Utils.BCReceiver");

        TestReceiver testReceiver = new TestReceiver();
        registerReceiver(testReceiver,filter);

        IntentFilter filter2 = new IntentFilter();
        filter2.setPriority(500);
        filter2.addAction("com.example.testarea.Utils.BCReceiver");
        TestReceiver2 testReceiver2 = new TestReceiver2();
        registerReceiver(testReceiver2,filter2);

        IntentFilter filter3 = new IntentFilter();
        filter3.setPriority(400);
        filter3.addAction("com.example.testarea.Utils.BCReceiver");
        TestReceiver3 testReceiver3 = new TestReceiver3();
        registerReceiver(testReceiver3,filter3);
    }


    public void initView(){
        bt_1 = findViewById(R.id.BCReceiver_bt_01);
        bt_2 = findViewById(R.id.BCReceiver_bt_02);
        bt_3 = findViewById(R.id.BCReceiver_bt_03);
        bt_4 = findViewById(R.id.BCReceiver_bt_04);
    }

    public void initAction(){

        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.BCReceiver_bt_01:
                intent = new Intent("leexy_BCR");
//                intent.setComponent("com.example.testarea2","com.example.testarea2.BC_receiver");
                intent.putExtra("leexy",123456);
                sendBroadcast(intent,null);
                break;
            case R.id.BCReceiver_bt_02:
                intent = new Intent("com.example.testarea.Utils.BCReceiver");
                intent.putExtra("abc",222);
                sendBroadcast(intent);
                sendOrderedBroadcast(intent,"");
                break;
            case R.id.BCReceiver_bt_03:
                intent = new Intent("com.example.testarea.Utils.BCReceiver");
                intent.putExtra("abc",333);
                sendBroadcast(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    
    class TestReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("::TestReceiver","");
            System.out.println("TestReceiver");
            Toast.makeText(context, "TestReceiver1", Toast.LENGTH_SHORT).show();
        }
    }

    class TestReceiver2 extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("::TestReceiver2","");
            System.out.println("TestReceiver2");
            Toast.makeText(context, "TestReceiver2", Toast.LENGTH_SHORT).show();
        }
    }

    class TestReceiver3 extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("::TestReceiver3","");
            System.out.println("TestReceiver3");
            Toast.makeText(context, "TestReceiver3", Toast.LENGTH_SHORT).show();
        }
    }
}



