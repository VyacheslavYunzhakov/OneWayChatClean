package com.example.onewaychatclean.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.example.onewaychatclean.chat.MapsActivity;
import com.example.onewaychatclean.model.Item;
import com.squareup.picasso.Picasso;


public class Locations {
    public static void loadLocations(ImageView imageView, Item item, Context context) {
        //imageView.setImageURI(Uri.parse(item.text_or_uri));
        String url =item.path;
        Log.d("myLogs", "" + url);
        Picasso.with(context)
                .load(url)
                .noFade()
                .into(imageView);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                double latitude = Double.parseDouble(url.substring(50, 68));
                double longitude = Double.parseDouble(url.substring(69, 88));
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                Log.d("myLogs", "" + longitude + ", " + latitude);
                context.startActivity(intent);
            }
        });
    }
}
