package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationTest extends AppCompatActivity {

    private Button bt_01;
    private Button bt_02;
    private Button bt_03;
    private Button bt_04;
    private Button bt_05;
    private Button bt_06;

    public static final int TYPE_NORMAL = 1;  // 普通通知
    public static final int TYPE_PROGRESS = 2;  // 下载进度的通知
    public static final int TYPE_BIG_TEXT = 3;  // BigTextStyle通知
    public static final int TYPE_INBOX = 4;  // InboxStyle
    public static final int TYPE_BIG_PICTURE = 5;  // BigPictureStyle
    public static final int TYPE_HANGUP = 6;  // hangup横幅通知

    NotificationManager mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        initView();

//        //CHANNEL_ID，渠道ID，Android 8.0及更高版本必须要设置
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "110")
//                //设置小图标
//                .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
//                //设置标题
//                .setContentTitle("TestTitle")
//                //设置内容
//                .setContentText("TextContent")
//                //设置等级
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//
//        //点击时想要打开的界面
//        Intent intent = new Intent(this, CalendarActivity.class);
//        //一般点击通知都是打开独立的界面，为了避免添加到现有的activity栈中，可以设置下面的启动方式
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        //创建activity类型的pendingIntent，还可以创建广播等其他组件
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this, "110")
//                .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
//                .setContentTitle("My notification")
//                .setContentText("Hello World!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                //设置pendingIntent
//                .setContentIntent(pendingIntent)
//                //设置点击后是否自动消失
//                .setAutoCancel(true);
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        //notificationId 相当于通知的唯一标识，用于更新或者移除通知
//        notificationManager.notify(TYPE_NORMAL, builder2.build());

        bt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时想要打开的界面
                Intent intent = new Intent(NotificationTest.this, CalendarActivity.class);
                //一般点击通知都是打开独立的界面，为了避免添加到现有的activity栈中，可以设置下面的启动方式
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //创建activity类型的pendingIntent，还可以创建广播等其他组件
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTest.this, 1, intent, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(NotificationTest.this, "110")
                        .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        //设置pendingIntent
                        .setContentIntent(pendingIntent)
                        //设置点击后是否自动消失
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificationTest.this);
                //notificationId 相当于通知的唯一标识，用于更新或者移除通知
                notificationManager.notify(TYPE_INBOX, builder2.build());
            }
        });

        bt_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时想要打开的界面
                Intent intent = new Intent(NotificationTest.this, MapActivity.class);
                //一般点击通知都是打开独立的界面，为了避免添加到现有的activity栈中，可以设置下面的启动方式
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //创建activity类型的pendingIntent，还可以创建广播等其他组件
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTest.this, 1, intent, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder2 = new NotificationCompat.Builder(NotificationTest.this, "120")
                        .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        //设置pendingIntent
                        .setContentIntent(pendingIntent)
                        //设置点击后是否自动消失
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotificationTest.this);
                //notificationId 相当于通知的唯一标识，用于更新或者移除通知
                notificationManager.notify(TYPE_INBOX, builder2.build());
            }
        });
    }

    public void initView(){
        bt_01 = findViewById(R.id.Noticification_bt_01);
        bt_02 = findViewById(R.id.Noticification_bt_02);
        bt_03 = findViewById(R.id.Noticification_bt_03);
        bt_04 = findViewById(R.id.Noticification_bt_04);
        bt_05 = findViewById(R.id.Noticification_bt_05);
        bt_06 = findViewById(R.id.Noticification_bt_06);
    }

    /**
     * 普通通知
     */
    public void Normal_notification(){
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//Ticker是状态栏显示的提示
        builder.setTicker("简单Notification");
//第一行内容  通常作为通知栏标题
        builder.setContentTitle("标题");
//第二行内容 通常是通知正文
        builder.setContentText("通知内容");
//第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("这里显示的是通知第三行内容！");
//ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
//builder.setContentInfo("2");
//number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        builder.setNumber(2);
//可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
//系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
//下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        Intent intent = new Intent(this, CalendarActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
//点击跳转的intent
        builder.setContentIntent(pIntent);
//通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        manager.notify(TYPE_NORMAL, notification);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.e("::onStart::","");
        System.out.println(getClass().toString()+"::onStart::");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.e("::onPause::","");
        System.out.println(getClass().toString()+"::onPause::");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.e("::onResume::","");
        System.out.println(getClass().toString()+"::onResume::");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.e("::onStop::","");
        System.out.println(getClass().toString()+"::onStop::");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Log.e("::onDestroy::","");
        System.out.println(getClass().toString()+"::onDestroy::");
//        if(bcReceiver!=null){
//            unregisterReceiver(bcReceiver);
//        }

    }
}