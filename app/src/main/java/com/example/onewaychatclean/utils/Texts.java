package com.example.onewaychatclean.utils;

import android.view.View;
import android.widget.TextView;

import com.example.onewaychatclean.model.Item;

public class Texts {
    public static void loadTextMessages(TextView textView, Item item) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(item.path);
    }
}
