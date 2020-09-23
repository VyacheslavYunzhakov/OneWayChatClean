package com.example.onewaychatclean.chat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.onewaychatclean.R;

import com.example.onewaychatclean.model.Item;
import com.example.onewaychatclean.model.buttons.ActionButton;
import com.example.onewaychatclean.model.buttons.AddButton;
import com.example.onewaychatclean.model.buttons.CloseButton;
import com.example.onewaychatclean.model.buttons.GeolocationButton;
import com.example.onewaychatclean.model.buttons.ImageActionButton;
import com.example.onewaychatclean.model.buttons.PhotoButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChatActivity extends AppCompatActivity implements ChatView, View.OnClickListener {

    private final int columns = 1;
    private int margin;
    FrameLayout frameLayout;

    ImageButton sendTextMessageButton;

    private static final int CAMERA_REQUEST = 0;


    @BindView(R.id.recyclerView)
            RecyclerView recyclerView;

    private MessagesAdapter messagesAdapter;

    private final int Pick_image = 1;
    SaverInDatabase saverInDatabase = new SaverInDatabase();

    AddButton addButton;
    CloseButton closeButton;
    GeolocationButton geolocationButton;
   // TextMessageButton textMessageButton;
    PhotoButton photoButton;
    ImageActionButton imageActionButton;
    LayoutInflater ltInflater;
    public ArrayList<ActionButton> floatingActionButtonListForExport = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ltInflater = LayoutInflater.from(this);

        frameLayout = findViewById(R.id.content_fragment);
        sendTextMessageButton= findViewById(R.id.sendMessageButton);
        sendTextMessageButton.setOnClickListener(this);

        RtlGridLayoutManager layoutManager = new RtlGridLayoutManager(getApplicationContext(), columns, 1, false);

        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewMargin decoration = new RecyclerViewMargin(40, columns);
        recyclerView.addItemDecoration(decoration);
        messagesAdapter = createMessagesAdapter();
        recyclerView.setAdapter(messagesAdapter);

        addButton = new AddButton(findViewById(R.id.addButton)
                , floatingActionButtonListForExport,this);
        closeButton = new CloseButton(findViewById(R.id.closeButton)
                , floatingActionButtonListForExport,this);
        geolocationButton = new GeolocationButton(findViewById(R.id.geolocationButton)
                , floatingActionButtonListForExport,this);
        //textMessageButton = new TextMessageButton(findViewById(R.id.textMessageButton)
         //       , floatingActionButtonListForExport,this);
        photoButton = new PhotoButton(findViewById(R.id.cameraButton)
                , floatingActionButtonListForExport,this);
        imageActionButton = new ImageActionButton(findViewById(R.id.albumButton)
                , floatingActionButtonListForExport,this);

        floatingActionButtonListForExport.addAll(Arrays.asList(addButton, closeButton,
                geolocationButton, photoButton, imageActionButton));

        MessagesPresenter messagesPresenter = new MessagesPresenter(this,this);
        messagesPresenter.init();
    }

    private MessagesAdapter createMessagesAdapter() {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new MessagesAdapter(imageHeight, imageWidth, this);
    }

    @Override
    public void showMessages(List<Item> items) {
        messagesAdapter.changeDataSet(items);
        recyclerView.setVisibility(View.VISIBLE);
        //recyclerView.smoothScrollToPosition(items.size());
    }

    @Override
    public void startImageIntent() {
        startActivityForResult(saverInDatabase.createImageIntent(),Pick_image);
    }

    @Override
    public void startPhotoIntent() {
        saverInDatabase.setContext(this);
        startActivityForResult(saverInDatabase.createPhotoIntent(),CAMERA_REQUEST);
    }

    @Override
    public void showGeolocation() {
        saverInDatabase.setContext(this);
        saverInDatabase.saveGeolocation();
    }

    @Override
    public void showFullImage(Item item) {
        FullscreenImage fullscreenImage = new FullscreenImage(item);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, fullscreenImage);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (requestCode == Pick_image) {
                saverInDatabase.setContext(this);
                saverInDatabase.createAndSaveImage(data);
            }
            if (requestCode == CAMERA_REQUEST){
                saverInDatabase.savePhoto(data);
            }
        }
    }

    @Override
    public void onClick(View v) {
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayoutSendMessage);
        saverInDatabase.createAndSaveTextMessage(relativeLayout);
    }

}