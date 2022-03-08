package com.example.testarea.Utils;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTool {

    private final String TAG = "Okhttptool::";
//    private final String address = "http://47.98.155.84:80/leetest";
    private final String address = "http://262tf89717.zicp.vip/HRK_server0729";
    //    private final String address = "http://192.168.101.6:8080/HRK_server0729";
//    private String address = "http://xcubj3.natappfree.cc/HRK_server0729";
    private final OkHttpClient client = new OkHttpClient();
    //    private final String address = "http://192.168.101.6:8080/HRK_server0729";
//    private String address = "http://xcubj3.natappfree.cc/HRK_server0729";
    public void execute_testConnect(){
        new Thread(() -> {
            try {
                testServerConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 测试服务器链接
     * @return 链接状态
     * @throws IOException string
     */
    public String testServerConnect() throws IOException {
        Log.e(TAG,"测试");
        Request request = new Request.Builder()
                .url(address+"/testConnect")
                .build();
        String res ;
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
//            System.out.println(response.code());
            assert response.body() != null;
            res = response.body().string();
//            System.out.println(getClass().toString()+"::res::"+res);
        }else{
            res = "CONNECT_TO_SERVER_FAIL";//response返回错误
        }
        return res;
    }

    public int execute_LoginCheck(String userid,String userpassword,String phonekey) throws Exception {

        int res ;
        OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("userid","李昕煜1")
                .add("userpassword","e10adc3949ba59abbe56e057f20f883e")
                .add("phonekey","745525d0853a4d5ba25ff1fb71abe764")
                .build();

        Request request = new Request.Builder()
                .url(address+"/login")
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
//            System.out.println(response.code());
            res = Integer.parseInt(response.body().string());
//            System.out.println(getClass().toString()+"::res::"+res);
        }else{
            res = 11;//response返回错误
        }
        return res;
    }
}