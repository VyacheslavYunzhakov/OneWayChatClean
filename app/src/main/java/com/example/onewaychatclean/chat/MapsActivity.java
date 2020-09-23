package com.example.onewaychatclean.chat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.onewaychatclean.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        waitForGeo();
    }
    private void waitForGeo(){
        if (latitude != 0 && longitude != 0) {
        //    LatLng yourLocation = new LatLng(-34, 151);

            LatLng yourLocation = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(yourLocation).title("you are here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((yourLocation),15));
        } else {
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                public void run() {
                    waitForGeo();
                }
            }, 1000);
        }
    }
}