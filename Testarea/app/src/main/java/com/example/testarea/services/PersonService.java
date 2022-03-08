package com.example.testarea.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;


public class PersonService extends Service {
    public PersonService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //    @Override
//    public IBinder onBind(Intent intent) {
//        return new IPerson.Stub() {
//            @Override
//            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//            }
//
//            @Override
//            public boolean eat(String food) throws RemoteException {
//                Log.d("PersionService", "this is Server");
//                Log.d("PersionService", "Persion eat " + food);
//                return true;
//            }
//        };
//    }


}