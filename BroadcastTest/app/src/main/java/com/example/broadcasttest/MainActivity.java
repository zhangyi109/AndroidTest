package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver1 myBroadcastReceiver;

    private IntentFilter intentFilter;
    private IntentFilter local_intentFliter;

    private LocalBroadcastReceiver localBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //发送系统全局广播
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                sendBroadcast(intent);
            }
        });

        //发送本地广播
        Button button2 = (Button) findViewById(R.id.button_local);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("localBroadcast");
                sendBroadcast(intent);
            }
        });

        myBroadcastReceiver = new MyBroadcastReceiver1();
        //创建本地广播实例
        localBroadcastReceiver = new LocalBroadcastReceiver();

        intentFilter = new IntentFilter();
        local_intentFliter = new IntentFilter();

        intentFilter.addAction("com.example.broadcasttest.MY_BROADCAST");
        local_intentFliter.addAction("localBroadcast");

        //注册广播监听器
        registerReceiver(myBroadcastReceiver,intentFilter);
        registerReceiver(localBroadcastReceiver,local_intentFliter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(localBroadcastReceiver);
    }

    class MyBroadcastReceiver1 extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "MyBroadcastReceiver1 received my broadcast", Toast.LENGTH_SHORT).show();

    }}
    //本地广播
    class LocalBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
}
}
