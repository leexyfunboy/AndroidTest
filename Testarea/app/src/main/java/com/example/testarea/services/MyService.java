package com.example.testarea.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private int flag = 0;
    private LocalBinder localBinder = new LocalBinder();


    public class LocalBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    public int getCount(){
        return 1;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService::","onCreate Thread::"+Thread.currentThread().getName());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService::","onStartCommand Thread::"+Thread.currentThread().getName()+" startId::"+startId);
        flag+=1;
        Log.e("flag::",String.valueOf(flag));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //此处在bindservice()之后会回调给onServiceConnected(ComponentName name, IBinder service)作为参数IBinder
        Log.e("MyService::","onBind Thread::"+Thread.currentThread().getName());
        flag +=2;
        Log.e("flag::",String.valueOf(flag));
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MyService::onDestroy()");
        Log.e("MyService::","onDestroy Thread::"+Thread.currentThread().getName());
    }


}