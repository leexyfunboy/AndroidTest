package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testarea.databinding.ActivityProgressBarBinding;

public class ProgressBarAC extends AppCompatActivity {

//    private ProgressBar progressBar;
//
//    private Button bt_01;


    private ActivityProgressBarBinding binding;
    private Button test_xcrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProgressBarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        initView();
        intiAction();
        binding.progressBar.setProgress(20);
    }

    public void initView(){
//        progressBar = findViewById(R.id.progressBar);
//        bt_01 = findViewById(R.id.progressbarAC_bt_01);
    }

    public void intiAction(){
        binding.progressbarACBt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressBar.getVisibility();
                binding.progressBar.setVisibility(View.INVISIBLE);
            }
        });

        test_xcrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProgressBarAC.this, "测试Xcrash", Toast.LENGTH_SHORT).show();
            }
        });
    }
}