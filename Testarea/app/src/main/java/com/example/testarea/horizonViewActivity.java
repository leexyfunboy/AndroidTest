package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;

import com.example.testarea.entity.HorizontalVpAdapter;


public class horizonViewActivity extends AppCompatActivity {

    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_view);

        ViewPager2 viewPager2 = findViewById(R.id.vp_h);
        HorizontalVpAdapter adapter = new HorizontalVpAdapter(this);
        viewPager2.setAdapter(adapter);

    }



}