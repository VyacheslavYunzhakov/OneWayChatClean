package com.example.onewaychatclean.chat;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onewaychatclean.R;
import com.example.onewaychatclean.model.Item;
import com.example.onewaychatclean.utils.Images;
import com.example.onewaychatclean.utils.Locations;
import com.example.onewaychatclean.utils.Texts;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesHolder extends RecyclerView.ViewHolder {



    @BindView(R.id.imageView)
    @Nullable ImageView imageView;

    @BindView(R.id.textMessage)
    @Nullable TextView textView;

    public static MessagesHolder create(Context context, int viewType, int imageHeight, int imageWidth) {
        View view = View.inflate(context, viewType, null);
        if (viewType == R.layout.horizontalimageview || viewType == R.layout.imageview) {
            ImageView imageView = view.findViewById(R.id.imageView);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = imageHeight;
            layoutParams.width = imageWidth;
            imageView.requestLayout();
        }
        if (viewType == R.layout.text) {
            TextView textView = view.findViewById(R.id.textMessage);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.requestLayout();
        }
        return new MessagesHolder(view);
    }

    private MessagesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(@NonNull Item item, Context context, ChatView chatView) {
        if (item.type.equals("image") || item.type.equals("camera")) {
            Images images = new Images();
            images.loadImages(imageView, item, chatView);
        }
        if (item.type.equals("location")) {
            Locations.loadLocations(imageView, item, context);
        }
        if (item.type.equals("text")){
            Texts.loadTextMessages(textView, item);
        }
    }
}
