package com.example.activitytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button_1 = (Button) findViewById(R.id.button_1);

        //测试edittext
        EditText edittext = (EditText) findViewById(R.id.edit_test_1);

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(FirstActivity.this, "you clicked button 1", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);
                String inputText = edittext.getText().toString();
                Toast.makeText(FirstActivity.this,inputText,Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "you click add item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you click remove item", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}

