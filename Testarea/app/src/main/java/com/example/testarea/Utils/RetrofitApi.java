package com.example.testarea.Utils;

import android.icu.text.IDNA;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {

    //get请求
    @GET("leetest/testChongZhou")
    Call<String> getJsonData();

    @FormUrlEncoded
    @POST("leetest/testChongZhou")
    Call<String> getReturn(@Field("userid") String userid, @Field("userpassword") String userpassword, @Field("phonekey") String phonekey);
}
