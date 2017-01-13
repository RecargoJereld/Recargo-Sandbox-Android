package com.recargo.recargosandbox.ui.map;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.recargo.recargosandbox.R;
import com.recargo.recargosandbox.RecargoSandboxApp;
import com.recargo.recargosandbox.data.api.model.PSLocation;
import com.recargo.recargosandbox.ui.MainActivity;
import com.recargo.recargosandbox.util.LocationUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.R.attr.tag;

/**
 * Created by jereld on 1/5/17.
 */

public class MapFragment extends Fragment implements MapContract.View {
    private static final String TAG = MapFragment.class.getSimpleName();

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    private GoogleMap googleMapView;

    @Inject
    MapPresenter presenter;

    private ArrayList<Marker> markers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).startLocationUpdates();

        DaggerMapComponent.builder()
                .mapPresenterModule(new MapPresenterModule(this,
                        RecargoSandboxApp.get(getActivity()).getPlugShareDataSource()))
                .build()
                .inject(this);

        addGoogleMap();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).stopLocationUpdates();
    }

    private void addGoogleMap() {
        // add map fragment as a nested fragment
        SupportMapFragment googleMapFragment = SupportMapFragment.newInstance();
        googleMapFragment.getMapAsync(presenter);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.map_container, googleMapFragment)
                .commit();
    }

    @Override
    public void setGoogleMapView(GoogleMap googleMapView) {
        this.googleMapView = googleMapView;
        setupGoogleMapView();

        // Move map to Venice initially
        Location venice = new Location("constant");
        venice.setLatitude(33.992);
        venice.setLongitude(-118.472);
        moveMapToLocation(venice);
    }

    private void setupGoogleMapView() {
        if (((MainActivity) getActivity()).hasLocationPermissionGranted()) {
            googleMapView.setMyLocationEnabled(true);
        }

        googleMapView.setOnCameraIdleListener(() -> {
            // do a location search at the new location
            presenter.loadLocationsInRegion(
                    googleMapView.getProjection().getVisibleRegion().latLngBounds);
        });

        googleMapView.setOnMarkerClickListener(marker -> {
            Log.v(TAG, "Marker clicked -  " + marker.getTitle());
            return false;
        });
    }

    @Override
    public void moveMapToCurrentLocation() {
        Location location = ((MainActivity) getActivity()).getLastLocation();
        moveMapToLocation(location);
    }

    private void moveMapToLocation(Location location) {
        if (location == null) return;

        CameraUpdate cameraUpdate = CameraUpdateFactory
                .newLatLngZoom(LocationUtils.locationToLatLng(location), 13f);
        googleMapView.moveCamera(cameraUpdate);
    }

    @Override
    public void addMarkersForLocations(List<PSLocation> psLocationList, boolean removeOldMarkers) {
        if (removeOldMarkers) {
            removeOldMarkers();
        }

        for (PSLocation psLocation : psLocationList) {
            LatLng latLng = new LatLng(psLocation.latitude, psLocation.longitude);
            Marker marker = googleMapView.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(psLocation.name));
            markers.add(marker);
        }
    }

    private void removeOldMarkers() {
        for (Marker marker : markers) {
            marker.remove();
        }
    }
}
