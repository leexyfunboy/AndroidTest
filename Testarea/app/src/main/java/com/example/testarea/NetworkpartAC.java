package com.example.testarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.testarea.Utils.OkHttpTool;
import com.example.testarea.databinding.ActivityMenuBinding;
import com.example.testarea.databinding.ActivityNetworkpartBinding;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class NetworkpartAC extends AppCompatActivity {

    /*   需要在非UI线程中更新的组件   */
    private TextView tx_01;

    private final int FLAG_OKHTTP = 1;

    private ActivityNetworkpartBinding binding;
    private MHandler mHandler = new MHandler(this);

    public static class MHandler extends Handler{

        private final WeakReference<NetworkpartAC> mWeakReference;

        public MHandler(NetworkpartAC activity){
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case 1:
                    NetworkpartAC activity = mWeakReference.get();
                    activity.binding.NetWorkACTx01.setText(msg.obj.toString());
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkpartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initView();
        initAction();
    }

    public void initView(){
        tx_01 = findViewById(R.id.NetWorkAC_tx01);
    }

    public void initAction(){
        binding.NetWorkACBt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpTool okHttpTool = new OkHttpTool();
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Looper.prepare();
                                Message message = new Message();
                                try {
                                    message.obj = okHttpTool.testServerConnect();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                message.what = FLAG_OKHTTP;
                                mHandler.sendMessage(message);
                                Looper.loop();
                            }
                        }
                ).start();

            }
        });
    }
}