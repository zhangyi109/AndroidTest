package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","tom");
                editor.putInt("age",24);
                editor.putBoolean("married",false);
                editor.apply();
            }
        });

        //读取按钮的点击事件
            Button restoreData = (Button) findViewById(R.id.restore_data);
            restoreData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                    String name = sharedPreferences.getString("name","");
                    int age = sharedPreferences.getInt("age",0);
                    boolean married = sharedPreferences.getBoolean("married",false);
                    Log.d("MainActivity","name is "+name);
                    Log.d("MainActivity","Age is : "+ age);
                    Log.d("MainActivity","Married : " + married);

                }
            });
    }
}