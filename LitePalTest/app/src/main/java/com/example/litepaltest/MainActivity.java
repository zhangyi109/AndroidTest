package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        Button create_database = (Button) findViewById(R.id.create_database);
        create_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });
        //添加书籍
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加第一本书
                Book book = new Book();
                book.setAuthor("guolin");
                book.setName("安卓第一行代码");
                book.setPaged(232);
                book.setPrice(134.2);
                book.setPress("人民邮电出版社");
                book.save();
                //添加第二本书
                Book book1 = new Book();
                book1.setAuthor("unknown");
                book1.setName("编译原理");
                book1.setPaged(600);
                book1.setPrice(71.25);
                book1.setPress("人民邮电出版社");
                book1.save();
            }
        });

//        更新数据
        Button updateData = (Button) findViewById(R.id.update_database);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(126.22);
                book.setAuthor("Author");
                book.updateAll("name = ? and author = ? ","编译原理","Author");
            }
        });
//删除数据
        Button deleteData = (Button) findViewById(R.id.delete_database);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class,"price > ? ","500");
            }
        });
        //查询数据
        Button queryData = (Button) findViewById(R.id.query_database);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> list = LitePal.findAll(Book.class);
                for (Book book:list){
                    Log.d("MainActivity","book name is : " + book.getName());
                    Log.d("MainActivity","book price is : " + book.getPrice());
                    Log.d("MainActivity","book author is : " + book.getAuthor());
                    Log.d("MainActivity","book pages is : " + book.getPaged());
                    Log.d("MainActivity","book press is : " + book.getPress());
                }
            }
        });
    }
}