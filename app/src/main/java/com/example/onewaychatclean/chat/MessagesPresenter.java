package com.example.onewaychatclean.chat;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.onewaychatclean.database.App;
import com.example.onewaychatclean.database.AppDatabase;
import com.example.onewaychatclean.model.Item;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MessagesPresenter {

    AppDatabase database = App.getInstance().getDatabase();
    ChatView chatView;
    ChatActivity chatActivity;

    public MessagesPresenter(ChatView chatView, ChatActivity chatActivity){
        this.chatActivity = chatActivity;
        this.chatView = chatView;
    }

    @SuppressLint("CheckResult")
    public void init() {
        database.itemDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    chatView.showMessages(items);
                }, throwable -> Log.d("myLogs", "There is an error in init:" + throwable));
    }
}
