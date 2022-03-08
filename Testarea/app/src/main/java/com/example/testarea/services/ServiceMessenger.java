package com.example.testarea.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

public class ServiceMessenger extends Service {

    private Messenger messenger = new Messenger(new ServiceHandler());

    public class ServiceHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case 1:
                    Log.e("ServiceMessenger::","1");
                    Messenger clientHAndler = msg.replyTo;
                    Message message = Message.obtain(null,123,0,0);
                    Bundle bundle = new Bundle();
                    bundle.putString("returnData","this is a return data");
                    message.setData(bundle);
                    try {
                        clientHAndler.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }



    public ServiceMessenger() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ServiceMessenger::","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ServiceMessenger::","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("ServiceMessenger::","onBind");
        return messenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ServiceMessenger::","onDestroy");

    }
}