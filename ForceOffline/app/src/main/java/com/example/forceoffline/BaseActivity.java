package com.example.forceoffline;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private ForceOfflineReceiver forceOfflineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        intentFilter = new IntentFilter();
        forceOfflineReceiver = new ForceOfflineReceiver();

        intentFilter.addAction("sendBroadcastForceoffline");
        registerReceiver(forceOfflineReceiver,intentFilter);

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (forceOfflineReceiver != null){
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    //强制下线广播接收器
    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("you are forced to offline. Please try to logi  again");
            builder.setCancelable(false);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityController.finishAll();
                    Intent intent1 = new Intent(context,LoginActivity.class);
                    context.startActivity(intent1);
                }
            });
            builder.show();
        }
    }
}
