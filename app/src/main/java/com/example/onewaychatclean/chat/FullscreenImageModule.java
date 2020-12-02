package com.example.onewaychatclean.chat;

import dagger.Module;
import dagger.Provides;

@Module
public class FullscreenImageModule {

    FullscreenImageProvider fullscreenImageProvider;

    @Provides
    FullscreenImageProvider provideFullscreenImageProvider(){
        if (fullscreenImageProvider == null){
            fullscreenImageProvider = new FullscreenImageProvider();

        }
        return fullscreenImageProvider;
    }
}
