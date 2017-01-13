package com.recargo.recargosandbox.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.recargo.recargosandbox.ContextModule;
import com.recargo.recargosandbox.GooglePlayServicesModule;
import com.recargo.recargosandbox.R;
import com.recargo.recargosandbox.ui.about.AboutFragment;
import com.recargo.recargosandbox.ui.list.ListFragment;
import com.recargo.recargosandbox.ui.map.MapFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jereld on 1/3/17.
 */

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener {
    private static final int REQ_PERMISSION_LOCATION = 891;

    private static final long LOCATION_UPDATE_INTERVAL = 10000; // ms
    private static final long FASTEST_LOCATION_UPDATE_INTERVAL =
            LOCATION_UPDATE_INTERVAL / 2; // ms


    private MapFragment mapFragment;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Inject
    GoogleApiClient googleApiClient;

    private boolean requestingLocationUpdates;
    private Location currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .contextModule(new ContextModule(this))
                .googlePlayServicesModule(new GooglePlayServicesModule(this, this, this))
                .build()
                .inject(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Fragment currentFragment =
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment == null) {
            showMap();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (requestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map:
                showMap();
                return true;
            case R.id.list:
                showList();
                return true;
            case R.id.about:
                showAbout();
                return true;
        }

        return false;
    }

    private void showMap() {
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
        }

        addFragmentToView(mapFragment);
    }

    private void showList() {
        ListFragment fragment = ListFragment.newInstance();
        addFragmentToView(fragment);
    }

    private void showAbout() {
        AboutFragment aboutFragment = AboutFragment.newInstance();
        addFragmentToView(aboutFragment);
    }

    private void addFragmentToView(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public Location getLastLocation() {
        return currentLocation;
//        if (hasLocationPermissionGranted()) {
//            return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        } else {
//            requestLocationPermission();
//            return null;
//        }
    }

    public boolean hasLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                REQ_PERMISSION_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    public void startLocationUpdates() {
        requestingLocationUpdates = true;

        if (hasLocationPermissionGranted() && googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                    createLocationRequest(),
                    this);
        } else {
            requestLocationPermission();
        }
    }

    public void stopLocationUpdates() {
        requestingLocationUpdates = false;

        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(LOCATION_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_LOCATION_UPDATE_INTERVAL);

        return locationRequest;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and a connection to Google APIs
        // could not be established. Display an error message, or handle
        // the failure silently
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}
}
