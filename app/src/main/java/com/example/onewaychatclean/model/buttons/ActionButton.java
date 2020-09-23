package com.example.onewaychatclean.model.buttons;

import android.view.View;

import com.example.onewaychatclean.R;
import com.example.onewaychatclean.chat.ChatView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public abstract class ActionButton  implements View.OnClickListener {

    List<ActionButton> floatingActionButtonListForExport;

    public FloatingActionButton floatingActionButton;

    ChatView chatView;

    public ActionButton(FloatingActionButton floatingActionButton,
                        List<ActionButton> floatingActionButtonListForExport, ChatView chatView){
        this.floatingActionButtonListForExport = floatingActionButtonListForExport;
        this.floatingActionButton = floatingActionButton;
        this.floatingActionButton.setOnClickListener(this);
        this.chatView = chatView;
    }


    public void onClick(View v) {
        for (int i = 0; i < floatingActionButtonListForExport.size(); i++) {
            if (floatingActionButtonListForExport.get(i).floatingActionButton.getId() != R.id.addButton) {
                floatingActionButtonListForExport.get(i).floatingActionButton.setVisibility(View.GONE);
            } else floatingActionButtonListForExport.get(i).floatingActionButton.setVisibility(View.VISIBLE);
        }
    }
}
