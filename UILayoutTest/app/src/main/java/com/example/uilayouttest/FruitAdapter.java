package com.example.uilayouttest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourseID;

    public FruitAdapter(Context context, int textViewResourseID, List<Fruit> objects){
        super(context,textViewResourseID,objects);
        resourseID = textViewResourseID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view ;
        if (convertView != null){
            view = convertView;
        }else
        {
            view = LayoutInflater.from(getContext()).inflate(resourseID,parent,false);
        }
        Fruit fruit = getItem(position);

        ImageView  imageView = (ImageView)view.findViewById(R.id.fruit_image);
        TextView textView = (TextView) view.findViewById(R.id.fruit_name);
        imageView.setImageResource(fruit.getImageId());
        textView.setText(fruit.getName());
        return  view;
    }
}
