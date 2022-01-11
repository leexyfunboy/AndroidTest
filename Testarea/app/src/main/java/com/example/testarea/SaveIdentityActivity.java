package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class SaveIdentityActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_identity);

        if (ActivityCompat.checkSelfPermission(SaveIdentityActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(SaveIdentityActivity.this,new String[] {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },MY_PERMISSION_REQUEST_CODE);
            return;
        }

//        String phoneInfo = getUUID();
//        Log.d("UUID",phoneInfo);
        try {
            //saveBitmap();
            Log.d("readkey()",readKey());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void saveBitmap() throws IOException {


        // 创建目录
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("sdCardDir",sdCardDir);
        File appDir = new File(sdCardDir,"Cache");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "hrk_key" + ".txt";//这里是创建一个TXT文件保存我们的UUID
        Log.d("appDir", String.valueOf(appDir));
        Log.d("fileName",fileName);
        File file = new File(appDir, fileName);
        if (!file.exists()) {
            Log.d("file.getpath",file.getPath());
            file.createNewFile();

        }
        //保存android唯一表示符
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(getUUID());
            fw.flush();
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    316a2 62c42 1241f 4ba6d 30254 66344 71
//    316a262c421241f4ba6d302546634471

    public static String readKey() throws IOException {
        // 创建目录
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File appDir = new File(sdCardDir, "CaChe");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "hrk_key" + ".txt";//这里是进行读取我们保存文件的名称
        File file = new File(appDir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = null;
        StringBuilder content=null;
        try {
            FileReader fr = new FileReader(file);
            content= new StringBuilder();
            reader = new BufferedReader(fr);
            String line;
            while ((line= reader.readLine())!=null){
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

}