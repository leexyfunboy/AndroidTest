package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AlertdialogAC extends AppCompatActivity {

    private Button bt_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog);

        initView();
        initAction();
    }

    public void initView(){
        bt_01 = findViewById(R.id.AlertdialogAC_bt_01);
    }

    public void initAction(){
        bt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListDialog();
            }
        });
    }

    /**
     * 列表对话框
     */
    private void itemListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertdialogAC.this);
//        builder.setTitle("选择你喜欢的课程：");
//        builder.setCancelable(true);
//        final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育","语文", "数学", "英语", "化学", "生物", "物理", "体育","语文", "数学", "英语", "化学", "生物", "物理", "体育"};
//        builder.setIcon(R.mipmap.ic_launcher)
//                .setItems(lesson, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
//                    }
//                }).create();
//        //设置正面按钮
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        //设置反面按钮
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
        List<Integer> list_num = new ArrayList<>();
        for(int i=1;i<=100;i++){
            list_num.add(i);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(AlertdialogAC.this,R.layout.item_listview_singal,R.id.item_listView_singal_tx,list_num);

        View dialogView = View.inflate(AlertdialogAC.this, R.layout.item_alertdialog_costom, null);
        AlertDialog dialog = builder.create();     //创建AlertDialog对象
        dialog.setView(dialogView);
        ListView listView = dialogView.findViewById(R.id.item_ad_listView);
        listView.setAdapter(arrayAdapter);
        dialog.show();                              //显示对话框
    }
}