package com.petrique.openstack;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        maps = googleMap;

        LatLng atm_location = new LatLng(44.418044, 26.084321);
        maps.addMarker(new MarkerOptions().position(atm_location).title("Academia Tehnica Militara"));
        maps.moveCamera(CameraUpdateFactory.newLatLng(atm_location));

    }
}
