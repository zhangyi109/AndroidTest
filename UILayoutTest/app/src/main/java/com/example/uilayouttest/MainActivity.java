package com.example.uilayouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_1 =  (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "you clicked button 1", Toast.LENGTH_SHORT).show();
            }
        });
        //Button 2:点击跳转到水平线性布局
        Button button_2 =(Button) findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Linear_horizontal.class);
                startActivity(intent);
            }
        });
        //Button 3：跳转到相对布局页面
        Button button_3 =(Button) findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RelativeLayoutTest.class);
                startActivity(intent);
            }
        });
        //跳转到framelayout的点击事件
        Button framelayout_button = (Button) findViewById(R.id.frame_button);
        framelayout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.activitytest.FrameLayout");
                startActivity(intent);
            }
        });
        //Button：跳转到自定义布局页面
        Button custom_BUTTON =(Button) findViewById(R.id.customlayout);
        custom_BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CustomLayoutTest.class);
                startActivity(intent);
            }
        });
//        跳转到listview界面的点击事件
        //使用显式intent
        Button listview_button = (Button) findViewById(R.id.listview_button);
        listview_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListViewTest.class);
                startActivity(intent);
            }
        });
    }
}