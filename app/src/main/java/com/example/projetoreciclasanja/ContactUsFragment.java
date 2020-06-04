package com.example.projetoreciclasanja;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactUsFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap map;

    public ContactUsFragment() {
    }

    SupportMapFragment mapFragment;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_location, container, false);

        return v;
    }
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
    super.onViewCreated(view, savedInstanceState);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
            mapFragment.getMapAsync(this);
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng brazil = new LatLng(-23.18601535, -45.885593);
        map.addMarker(new MarkerOptions().position(brazil).title("Marker in Brazil"));
        map.moveCamera(CameraUpdateFactory.newLatLng(brazil));
    }
}
