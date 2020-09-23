package com.example.onewaychatclean.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.example.onewaychatclean.chat.ChatView;
import com.example.onewaychatclean.chat.MapsActivity;
import com.example.onewaychatclean.model.Item;

public class Locations {
    public static void loadLocations(ImageView imageView, Item item, Context context) {
        imageView.setImageURI(Uri.parse(item.text_or_uri));
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MapsActivity.class));
            }
        });
    }
}
