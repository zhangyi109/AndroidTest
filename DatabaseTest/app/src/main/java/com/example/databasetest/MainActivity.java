package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   private MyDatabaseHelper myDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        myDatabaseHelper = new MyDatabaseHelper(this,"Bookstore.db",null,2);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getReadableDatabase();
            }
        });

        //向数据库中添加数据
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","安卓第一行代码");
                values.put("author","guolin");
                values.put("pages","454");
                values.put("price","126.1");
                db.insert("Book",null,values);
                values.put("name","Java核心技术");
                values.put("author","adasds");
                values.put("pages","645");
                values.put("price","233");
                db.insert("Book",null,values);
            }
        });

        //更新数据
        Button upgradeData = (Button) findViewById(R.id.upgrade_data);
        upgradeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put("price","10");
                db.update("Book",values,"name = ?",new String[]{"安卓第一行代码"});
            }
        });

        //删除数据
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
                db.delete("Book","pages > ?",new String[]{"500"});
            }
        });

        //查询数据
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
                //查询表中的所有数据
                Cursor cursor = db.query("Book",null,null,null,null,null,null);

                if (cursor.moveToFirst()){
                    do {
                        //遍历cursor对象，打印出所有数据
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                        @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity","book name is : " + name);
                        Log.d("MainActivity","book author is : " + author);
                        Log.d("MainActivity","book pages is : " + pages);
                        Log.d("MainActivity","book price is : " + price);

                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}