package com.example.onewaychatclean.chat;

import com.example.onewaychatclean.utils.Images;

import dagger.Component;


@Component  (modules = {FullscreenImageModule.class})
public interface AppComponent {
    FullscreenImageProvider getFullscreenImageProvider();
    void injects (Images images);
}


