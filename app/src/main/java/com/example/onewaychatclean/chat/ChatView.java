package com.example.onewaychatclean.chat;

import com.example.onewaychatclean.model.Item;
import java.util.List;

public interface ChatView {
    void showMessages(List<Item> items);
    void startImageIntent();
    void startPhotoIntent();
    void showGeolocation();
}
