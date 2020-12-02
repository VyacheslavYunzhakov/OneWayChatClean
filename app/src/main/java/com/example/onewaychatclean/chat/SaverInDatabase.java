package com.example.onewaychatclean.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.onewaychatclean.R;
import com.example.onewaychatclean.database.App;
import com.example.onewaychatclean.database.AppDatabase;
import com.example.onewaychatclean.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SaverInDatabase extends AppCompatActivity {

    private LocationManager locationManager;
    public Intent photoPickerIntent;
    Context chatActivityContext;
    public static Double latitude,longitude;
    private Uri imageURI;
    private Item item= new Item();
    AppDatabase database = App.getInstance().getDatabase();
    private Uri photoURI;

    public Intent createImageIntent() {
        photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        return photoPickerIntent;
    }

    public Intent createPhotoIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "test.jpg");
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
         photoURI = FileProvider.getUriForFile(chatActivityContext,
                "com.example.onewaychatclean.fileprovider",
                photoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        return cameraIntent;
    }

    public void createAndSaveImage(Intent data) {
        final int imageOrienationLayout;

        Uri selectedImageUri = data.getData();


        InputStream imageStream = null;

        try {
            imageStream = chatActivityContext.getContentResolver().openInputStream(selectedImageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        Drawable drawable = new BitmapDrawable(chatActivityContext.getResources(), bitmap);
        if (drawable.getIntrinsicHeight() >drawable.getIntrinsicWidth()){
            imageOrienationLayout = R.layout.imageview;
        } else {imageOrienationLayout = R.layout.horizontalimageview;
        }

        try {
            File imageFile = createImageFile();
            FileOutputStream out = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, out);
            Log.d("myLogs", imageFile.getAbsolutePath());
            imageURI = FileProvider.getUriForFile(this,
                    "com.example.onewaychatclean.fileprovider",
                    imageFile);
        } catch (IOException e) {
            Log.d ("myLogs", "" + e);
            e.printStackTrace();
        }

        item.path = imageURI.toString();
        item.idOfView = R.id.imageView;
        item.idOfXML = imageOrienationLayout;
        item.time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        item.type = "image";
        database.itemDao().insertOne(item);
    }

    public void setContext(Context context){
        chatActivityContext = context;
    }

    public void savePhoto(Intent data) {

        item.path =photoURI.toString();
        item.idOfView = R.id.imageView;
        item.idOfXML = R.layout.imageview;
        item.time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        item.type = "camera";
        database.itemDao().insertOne(item);
    }

    public void createAndSaveTextMessage(RelativeLayout relativeLayout) {
        EditText saveText = relativeLayout.findViewById(R.id.inputMessage);
        item.path = saveText.getText().toString();
        saveText.setText("");
        item.idOfView = R.id.textMessage;
        item.idOfXML = R.layout.text;
        item.time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        item.type = "text";
        database.itemDao().insertOne(item);

    }

    public void saveGeolocation() {
        locationManager = (LocationManager) chatActivityContext.getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(chatActivityContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(chatActivityContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(chatActivityContext, "Permission failed", Toast.LENGTH_SHORT);
            toast.show();
            chatActivityContext.startActivity(new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000 * 1, 1, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000 * 1, 1,
                locationListener);


    }
    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            if (latitude!= null && longitude != null){
                item.path = "https://maps.google.com/maps/api/staticmap?center=" + latitude + "," + longitude +
                        "&zoom=15&size=200x200&sensor=false&key=AIzaSyBbnBQlCHfSyRKz9QPdOxu9ySfRLsYWZbM";
                item.idOfView = R.id.imageView;
                item.idOfXML = R.layout.location_image;
                item.time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                item.type = "location";
                database.itemDao().insertOne(item);
            } else onLocationChanged(location);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };
    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = chatActivityContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        Log.d("myLogs", "storageDir:" + storageDir);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        String mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
