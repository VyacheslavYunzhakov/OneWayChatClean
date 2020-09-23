package com.example.onewaychatclean.utils;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.example.onewaychatclean.chat.ChatView;

import com.example.onewaychatclean.model.Item;

public class Images {
    public void loadImages(ImageView imageView, Item item, ChatView chatView) {
        imageView.setImageURI(Uri.parse(item.text_or_uri));
        imageView.setVisibility(View.VISIBLE);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatView.showFullImage(item);
            }
        });
    }
}
