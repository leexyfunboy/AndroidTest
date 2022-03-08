package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testarea.databinding.ActivityServiceBinding;
import com.example.testarea.services.MyService;
import com.example.testarea.services.ServiceMessenger;
import com.example.testarea2.IPerson;
import com.example.testarea2.IProject;

import java.util.List;

public class ServiceAC extends AppCompatActivity {

    //viewbinding
    private ActivityServiceBinding binding;
    //为了测试bindService();使用Binder
    private ServiceConnection conn1;
    //为了测试bindService();使用Messenger
    private ServiceConnection conn2;
    //为了测试bindService();使用AIDL
    private ServiceConnection conn3;
    //为了测试bindService();使用Binder
    private MyService myBindService;
    //为了测试bindService();使用Messenger
    private Messenger messengerService; //用于向服务发送
    private Messenger messengerRetrive; //用于接收服务返回
    private boolean mBound; //flag 表示服务连接是否已成立
    //用于启动MyServie
    Intent intent1 ;
    Intent intent2;
    private IProject mService;


    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.e("ServiceAC::","onCreate()");

        initAction();

        messengerRetrive = new Messenger(new HandlerRetrive());
        intent1 = new Intent(ServiceAC.this, MyService.class);
        intent2 = new Intent(ServiceAC.this,ServiceMessenger.class);

//        Intent serviceintent = new Intent();
//        serviceintent.setAction("com.example.testarea2.Services.TestService");
//        serviceintent.setPackage("com.example.testarea2.Services");
//        startService(serviceintent);

//        Intent mIntent=new Intent();//辅助Intent
//        mIntent.setAction("com.example.testarea2.Services.TestService");
//        final Intent serviceIntent=new Intent(getExplicitIntent(this,mIntent));
//        startService(serviceIntent);

        conn1 = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.LocalBinder binder = (MyService.LocalBinder)service;
                myBindService = binder.getService();
                myBindService.getCount();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myBindService = null;
            }
        };

        conn2 = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                messengerService = new Messenger(service);
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myBindService = null;
                mBound = false;
            }
        };

        conn3 = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IProject.Stub.asInterface(service);
                try {
                    String s = mService.getProjectinfo();
                    Log.e("ServiceAC::",s);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myBindService = null;
                mBound = false;
            }
        };



    }



    public void initAction(){
        binding.btStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent1 = new Intent(ServiceAC.this, MyService.class);
                startService(intent1);

                //waitForOneMinute(intent);   //等待五秒钟后，关闭Service
            }
        });

        binding.btBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent1 = new Intent(ServiceAC.this, MyService.class);
                bindService(intent1,conn1,BIND_AUTO_CREATE);
            }
        });

        binding.btMessengerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent2,conn2,BIND_AUTO_CREATE);
                if(mBound){
                    Message msg = Message.obtain(null,1,0,0);
                    msg.replyTo = messengerRetrive;
                    try {
                        messengerService.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        binding.btAIDLService.setOnClickListener(new View.OnClickListener() {
            private android.widget.Toast Toast;

            @Override
            public void onClick(View v) {
                PackageManager p = getPackageManager();
                Intent in=p.getLaunchIntentForPackage("com.example.testarea2");
                if(in!=null)
                {
                    in.putExtra("name","zp");
                    startActivity(in);
                }
                else
                {
                    Toast.makeText(ServiceAC.this, "哟，赶紧下载安装这个APP吧", Toast.LENGTH_LONG).show();
                }
                Log.e("ServiceAC","点击");
                Intent intent = new Intent();
                intent.setClassName("com.example.testarea2","com.example.testarea2.Services.AIDLService");
//                intent.setAction("com.example.testarea2.Services.AIDLService");
//                intent.setPackage("com.example.testarea2");
                bindService(intent,conn3,BIND_AUTO_CREATE);
            }
        });

        binding.btTestIntentFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.testarea.services.MyService");
                intent.setPackage(getPackageName());
                Log.e("ServiceAC::","packageName= "+getPackageName());
                startService(intent);
            }
        });

        binding.btStopStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent1);   //关闭MyService
            }
        });

        binding.btStopAllService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class HandlerRetrive extends Handler {
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 123:
                    Log.e("接收到回复：：",msg.getData().getString("returnData"));
                    break;
            }
        }
    }

    public void waitForOneMinute(Intent intent){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        stopService(intent);
                    }
                }
        ).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ServiceAC::", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ServiceAC::", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("ServiceAC::", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("ServiceAC::", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ServiceAC::", "onDestroy()");
    }
}