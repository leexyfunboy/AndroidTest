package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LocationList extends AppCompatActivity {

    private TextView tx_locationData;

    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        initView();
        sp = getSharedPreferences("LocationData",MODE_PRIVATE);
        Gson gson = new Gson();
        ArrayList<String> list_locationData = new ArrayList<>();
        if(sp.getString("locationDatas",null)!=null){
            list_locationData = gson.fromJson(sp.getString("locationDatas",null), new TypeToken<ArrayList<String>>(){}.getType());
        }
        String showData = "";
        for(String data : list_locationData){
            showData += data+"\n";
        }
        tx_locationData.setText(showData);
    }

    public void initView(){
        tx_locationData= findViewById(R.id.locationList_tx_01);
    }

    public void initAction(){

    }
}