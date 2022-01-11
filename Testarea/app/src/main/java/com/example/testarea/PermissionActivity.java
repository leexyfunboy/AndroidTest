package com.example.testarea;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import static android.os.Build.getSerial;

public class PermissionActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        checkPermission();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkPermission(){
        String[] permission = new String[]{Manifest.permission.READ_SMS,
                                            Manifest.permission.READ_PHONE_STATE,
                                            Manifest.permission.READ_PRECISE_PHONE_STATE,
                                            Manifest.permission.READ_PHONE_NUMBERS};

        String[] permission2 = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.READ_EXTERNAL_STORAGE};

        if(ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED  &&
                ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_PHONE_NUMBERS)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PermissionActivity.this,permission,1);
        }else{
            Toast.makeText(this, "已经获取权限", Toast.LENGTH_SHORT).show();
        }

        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId();
        System.out.println("deviceid:: "+deviceid);
        String tel = tm.getLine1Number();
        System.out.println("tel:: "+tel);
        String  imei = tm.getSimSerialNumber();
        System.out.println("imei:: "+imei);
        String imsi = tm.getSubscriberId();
//        int simState = tm.getSimState();
        System.out.println("imsi:: "+imsi);
        String androidId = getAndroidId(this);
        Log.d("androidid:: ", androidId);
        TelephonyManager tm2 = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String SimSerialNumber = tm2.getSimSerialNumber();
        System.out.println("SimSerialNumber:: "+SimSerialNumber);
        String serial = Build.SERIAL;
        System.out.println("serial::: "+serial);



//        if(ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED  &&
//                ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(PermissionActivity.this,permission2,1);
//        }
    }

    /**
     * 获得设备的AndroidId
     *
     * @param context 上下文
     * @return 设备的AndroidId
     */
    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
}