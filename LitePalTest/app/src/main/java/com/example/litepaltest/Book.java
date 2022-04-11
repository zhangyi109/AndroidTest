package com.example.litepaltest;

import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {

    private int id;

    private  String author;

    private double price;

    private int paged;

    private  String name;

    private String press;

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPaged() {
        return paged;
    }

    public void setPaged(int paged) {
        this.paged = paged;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
