package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BroadcastreceiverActivity extends AppCompatActivity {

    private Button bt_1;
    DynamicReceiver dynamicReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);

        initView();
        initAction();
        //实例化IntentFilter对象
        IntentFilter filter = new IntentFilter();
        filter.addAction("panhouye");
        dynamicReceiver = new DynamicReceiver();
        //注册广播接收
        registerReceiver(dynamicReceiver,filter);

    }


    public void initView(){
        bt_1 = findViewById(R.id.bt_01);
    }

    public void initAction(){
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //创建Intent，指定要发送的广播的类型action
//                Intent intent = new Intent("com.example.testarea.Utils.BCReceiver");
//                //发送显示广播，设置广播接收者的路径:第一个参数是包名路径；第二个参数是类名路径
//                intent.setComponent(new ComponentName("com.example.testarea.Utils",
//                        "com.example.testarea.Utils.BCReceiver"));
//                //创建Bundle
//                Bundle bundle = new Bundle();
//                //储存要发送的广播消息内容
//                bundle.putString("message","hello broadcast receiver");
//                intent.putExtra("bundle",bundle);
//                //发送广播
//                sendBroadcast(intent);
//                Toast.makeText(BroadcastreceiverActivity.this, "点击", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction("panhouye");
                intent.putExtra("sele","潘侯爷");
                sendBroadcast(intent);
            }
        });
    }


}

class DynamicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //通过土司验证接收到广播
        Toast.makeText(context,"动态广播："+ intent.getStringExtra("sele"), Toast.LENGTH_SHORT);

    }
}

