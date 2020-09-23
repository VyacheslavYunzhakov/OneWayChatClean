package com.example.onewaychatclean.model.buttons;

import android.util.Log;
import android.view.View;

import com.example.onewaychatclean.R;
import com.example.onewaychatclean.chat.ChatView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class AddButton extends ActionButton {


    public AddButton(FloatingActionButton floatingActionButton, List<ActionButton> floatingActionButtonListForExport, ChatView chatView) {
        super(floatingActionButton, floatingActionButtonListForExport, chatView);
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < floatingActionButtonListForExport.size(); i++) {
            if (floatingActionButtonListForExport.get(i).floatingActionButton.getId() != R.id.addButton) {
                floatingActionButtonListForExport.get(i).floatingActionButton.setVisibility(View.VISIBLE);
            }
            else floatingActionButtonListForExport.get(i).floatingActionButton.setVisibility(View.GONE);
        }
    }

}
