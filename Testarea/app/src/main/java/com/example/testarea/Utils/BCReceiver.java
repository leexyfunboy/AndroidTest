package com.example.testarea.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BCReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//获取到发送的广播的内容
//        String message = (String) intent.getBundleExtra("bundle").get("message");
//        Toast.makeText(context, "接收到发送的广播消息："+message, Toast.LENGTH_LONG).show();
        int msg = intent.getIntExtra("abc",0);
        Log.d("BCReceiver::get","收到"+msg);
    }
}
