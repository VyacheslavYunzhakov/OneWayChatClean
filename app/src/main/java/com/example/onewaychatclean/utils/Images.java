package com.example.onewaychatclean.utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.example.onewaychatclean.chat.FullscreenImageProvider;
import com.example.onewaychatclean.database.App;
import com.example.onewaychatclean.model.Item;

import javax.inject.Inject;

public class Images {
    @Inject
    FullscreenImageProvider fullscreenImageProvider;

    public void loadImages(ImageView imageView, Item item) {
        App.getComponent().injects(this);
        imageView.setImageURI(Uri.parse(item.path));
        imageView.setVisibility(View.VISIBLE);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenImageProvider.getFullscreenImageProvider().showFullImage(item);
                //chatView.showFullImage(item);
            }
        });
    }
}
