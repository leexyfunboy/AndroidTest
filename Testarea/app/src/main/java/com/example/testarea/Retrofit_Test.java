package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;

import com.example.testarea.Utils.GetRequest_Interface;
import com.example.testarea.Utils.RetrofitApi;
import com.example.testarea.Utils.RetrofitData;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;

public class Retrofit_Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://47.98.155.84:80/") //设置网络请求的Url地址
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 创建 网络请求接口 的实例
        RetrofitApi mRetrofit = retrofit.create(RetrofitApi.class);

        //对 发送请求 进行封装
//        Call<String> call = mRetrofit.getReturn("李昕煜1","123456","745525d0853a4d5ba25ff1fb71abe764");
        Call<String> call = mRetrofit.getJsonData();
        call.enqueue(new Callback() {
            //请求成功时回调
            @Override
            public void onResponse(Call call, Response response) {
                //请求处理,输出结果
                Log.e("RetrofitAC::response","");
                System.out.println(response.body());
            }
            //请求失败时候的回调
            @Override
            public void onFailure(Call call, Throwable throwable) {
                //请求处理,输出结果
                Log.e("RetrofitAC::Failure","连接失败"+throwable.getMessage());
                System.out.println("连接失败");
            }
        });

//        //同步请求
//        try {
//            Response response = call.execute();
//            System.out.println(response.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }



}
