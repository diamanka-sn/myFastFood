package com.univ.myfastfood;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.univ.myfastfood.databinding.FragmentRestaurantBinding;

public class RestaurantActivity extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FragmentRestaurantBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return  view;
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
        mMap = googleMap; //marqueur

        // mysFastFood position
        LatLng mysFastFood = new LatLng(12.556213286050344, -16.265091082316694);
        mMap.addMarker(new MarkerOptions().position(mysFastFood).title("MyFastFood").snippet("Contact: 33 879 51 72, Site: myFastFood.sn"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mysFastFood,13));//Zoom sur un point du maps

        CircleOptions co = new CircleOptions()
                .center(mysFastFood)
                .radius(600)
                .fillColor(Color.GREEN)
                .strokeColor(Color.BLUE)
                .strokeWidth(5);
        mMap.addCircle(co);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if(marker.getTitle().equals("MyFastFood")){
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:778795172"));
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}