package com.example.onewaychatclean.chat;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onewaychatclean.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesHolder> {

    private final List<Item> mItems;
    private final int mImageHeight;
    private final int mImageWidth;
    ChatView chatView;
    Context context;

    public MessagesAdapter(int imageHeight, int imageWidth, ChatView chatView){
        this.chatView = chatView;
        mItems = new ArrayList<>();
        mImageHeight = imageHeight;
        mImageWidth = imageWidth;
    }

    public int getItemViewType(int position){
        Item item = mItems.get(position);
        return item.idOfXML;
    }
    @NonNull
    @Override
    public MessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return MessagesHolder.create(parent.getContext(), viewType, mImageHeight, mImageWidth);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesHolder holder, int position) {
        Item item = mItems.get(position);
        holder.bind(item, context, chatView);

        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void changeDataSet(List<Item> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
