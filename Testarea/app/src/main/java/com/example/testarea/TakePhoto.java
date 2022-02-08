package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePhoto extends AppCompatActivity {

    private ImageButton bt_takePhoto;
    private ImageView imgView;
    final int TAKE_PICTURE = 1;
    private Date date;
    private SimpleDateFormat format;

    private String mFilePath;
    private String fileName;
    private static int REQUEST_CAMERA_1 = 1;
    private static int REQUEST_CAMERA_2 = 2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        
        initView();
        initAction();
    }
    
    public void initView(){
        bt_takePhoto = findViewById(R.id.takePhoto_bt);
        imgView = findViewById(R.id.takePhoto_imageView);
    }
    
    public void initAction(){
        bt_takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PICTURE);
                openCamera_2();
            }
        });
    }

    // 拍照后存储并显示图片
    private void openCamera_2() {

        File fileDir = new File(Environment.getExternalStorageDirectory(),"Pictures");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        fileName = "IMG_" + System.currentTimeMillis() + ".jpg";

        mFilePath = fileDir.getAbsolutePath()+"/"+ fileName;
        Uri uri = null;
        ContentValues contentValues = new ContentValues();
        //设置文件名

        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/Pictures");
        }else {

            contentValues.put(MediaStore.Images.Media.DATA, mFilePath);
        }
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/JPEG");
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 启动系统相机

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA_2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回数据
            if (requestCode == REQUEST_CAMERA_2) {
                try {
                    //查询的条件语句
                    String selection = MediaStore.Images.Media.DISPLAY_NAME + "=? ";
                    //查询的sql
                    //Uri：指向外部存储Uri
                    //projection：查询那些结果
                    //selection：查询的where条件
                    //sortOrder：排序
                    Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.Media._ID},selection,new String[]{fileName},null);
                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            Uri uri =  ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getLong(0));
                            Log.i("luingssd","@"+uri);
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
                            imgView.setImageBitmap(bitmap);// 显示图片
                        }while (cursor.moveToNext());
                    }else {
                        Toast.makeText(this,"no photo",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        date = new Date();
//        format = new SimpleDateFormat("yyyyMMddHHmmss");
//        String nowTime = format.format(date);
//        if (requestCode == TAKE_PICTURE) {
//            if (resultCode == RESULT_OK) {
//                Bitmap bm = (Bitmap) data.getExtras().get("data");
//                imgView.setImageBitmap(bm);//想图像显示在ImageView视图上，private ImageView img;
////                File myCaptureFile = new File("sdcard/"+nowTime+".jpg");
////                if(myCaptureFile==null){
////                    Log.d("存储路径为空","");
////                }
//                // 创建目录
//                //获取内部存储状态
//                String state = Environment.getExternalStorageState();
//                //如果状态不是mounted，无法读写
//                if (!state.equals(Environment.MEDIA_MOUNTED)) {
//                    return;
//                }
//                String sdCardDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//                Log.d("sdCardDir",sdCardDir);
//                File appDir = new File(sdCardDir+"/hrk_pic","cache");
//                if (!appDir.exists()) {
//                    appDir.mkdir();
//                }
//                String fileName = nowTime;//这里是创建一个TXT文件保存我们的UUID
////        Log.d("appDir", String.valueOf(appDir));
////        Log.d("fileName",fileName);
//                File file = new File(appDir, fileName);
//                Log.d("sdCardDir",sdCardDir+"/hrk_pic/"+nowTime);
//                try {
//
//                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//                    /* 采用压缩转档方法 */
//                    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
//
//                    /* 调用flush()方法，更新BufferStream */
//                    bos.flush();
//
//                    /* 结束OutputStream */
//                    bos.close();
//                } catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}