package com.example.onewaychatclean.model.buttons;

import android.view.View;

import com.example.onewaychatclean.chat.ChatView;
import com.example.onewaychatclean.chat.SaverInDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class ImageActionButton extends ActionButton {


    public ImageActionButton(FloatingActionButton floatingActionButton, List<ActionButton> floatingActionButtonListForExport, ChatView chatView) {
        super(floatingActionButton, floatingActionButtonListForExport, chatView);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //SaverInDatabase saverInDatabase = new SaverInDatabase();
        chatView.startImageIntent();
    }
}
