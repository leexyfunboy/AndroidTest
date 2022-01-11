package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePhoto extends AppCompatActivity {

    private ImageButton bt_takePhoto;
    private ImageView imgView;
    final int TAKE_PICTURE = 1;
    
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
                startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), TAKE_PICTURE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                imgView.setImageBitmap(bm);//想图像显示在ImageView视图上，private ImageView img;
                File myCaptureFile = new File("sdcard/1645.jpg");
                try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                    /* 采用压缩转档方法 */
                    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);

                    /* 调用flush()方法，更新BufferStream */
                    bos.flush();

                    /* 结束OutputStream */
                    bos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}