package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TimeActivity extends AppCompatActivity {

    private Button bt_01;

    private static final int MIN_CLICK_DELAY_TIME = 5000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = false;
            System.out.println("curClickTime == "+curClickTime);
            System.out.println("lastClickTime == "+lastClickTime);
            System.out.println("curClickTime - lastClickTime == "+(curClickTime - lastClickTime));
            lastClickTime = curClickTime;
        }else{
            flag = true;
        }

        return flag;
    }

    private Handler handler  = new Handler();
    private Runnable runnable = new Runnable(){
        public void run(){
            System.out.println("@");
            System.out.println("#");
            handler.postDelayed(this,10000);//定时时间
            //handler.postDelayed(runnable,1000);
            handler.removeCallbacks(runnable);
        }
    };

    private Runnable runnable2 = new Runnable(){
        public void run(){
            Toast.makeText(TimeActivity.this, "时间", Toast.LENGTH_SHORT).show();
            handler.postDelayed(this,5000);//定时时间
            bt_01.setTextColor(0x000000);
            //handler.postDelayed(runnable,1000);
            //handler.removeCallbacks(runnable);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        bt_01 = findViewById(R.id.button);

        initAciton();
//        runnable.run();
//        runnable2.run();

    }

    public void initAciton(){
//        bt_01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bt_01.setTextColor(0xff99ff);
//                System.out.println("变色");
//                runnable2.run();
//            }
//        });

        bt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFastClick()) {
                    System.out.println("5秒内");
                    Log.e("TimeActivity","5秒内");
                    bt_01.setClickable(false);
                }else{
                    System.out.println("####");
                    bt_01.setClickable(true);
                }
            }
        });
    }
}