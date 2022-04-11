package com.example.forceoffline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText usrEdit;
    private EditText passEdit;
    private Button login;

    //sharedpreferences测试
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usrEdit = (EditText) findViewById(R.id.usrEditview);
        passEdit = (EditText) findViewById(R.id.passwordEditview);

        login = (Button) findViewById(R.id.login);

        //checkBox
        rememberPass = (CheckBox) findViewById(R.id.remPassCheckbox);

        //SharedPreferneces
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //是否记住密码
        boolean isRemember = pref.getBoolean("rememberpass",false);

        if (isRemember){
             String usr = pref.getString("usr","");
             String password = pref.getString("pass","");
             usrEdit.setText(usr);
             passEdit.setText(password);
             rememberPass.setChecked(true);
        }
        //设置点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = usrEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if (usr.equals("admin") && pass.equals("123456")){

                    //记住密码实现逻辑
                    editor = pref.edit();
                    if(rememberPass.isChecked()){//如果复选框被选中
                        editor.putString("usr",usr);
                        editor.putString("pass",pass);
                        editor.putBoolean("rememberpass",true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "请重新输入账号密码", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}