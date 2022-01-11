package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testarea.Utils.Tcpchat;

public class TcpTest extends AppCompatActivity {

    private Button bt_01;
    private EditText ed_01;
    private TextView tx_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_test);

        bt_01 = findViewById(R.id.tcptest_bt01);
        ed_01 = findViewById(R.id.tcptest_ed01);
        tx_01 = findViewById(R.id.tcptest_tx_01);


        bt_01.setOnClickListener(v -> {
            Tcpchat tcpchat = new Tcpchat();
            tcpchat.chat(ed_01.getText().toString());
            System.out.println("hhhhhhhhhhhhhh");
        });

    }
}