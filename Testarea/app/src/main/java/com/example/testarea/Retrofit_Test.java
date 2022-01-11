package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.testarea.Utils.GetRequest_Interface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofit_Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://262tf89717.zicp.vip/HRK_server0729") //设置网络请求的Url地址
                .build();

        // 创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call call = request.getCall("");
        call.enqueue(new Callback() {
            //请求成功时回调
            @Override
            public void onResponse(Call call, Response response) {
                //请求处理,输出结果
                System.out.println(response.body());
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

        //同步请求
        try {
            Response response = call.execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
