package com.recargo.recargosandbox.ui.map;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.BaseDataSource;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jereld on 1/5/17.
 */

public final class MapPresenter implements MapContract.Presenter {
    private static final String TAG = MapPresenter.class.getSimpleName();

    @VisibleForTesting
    public static final int QUERY_COUNT = 25;

    private final MapContract.View mapView;
    private final BaseDataSource plugShareDataSource;

    @Inject
    public MapPresenter(MapContract.View mapView, BaseDataSource plugShareDataSource) {
        this.mapView = mapView;
        this.plugShareDataSource = plugShareDataSource;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapView.setGoogleMapView(googleMap);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadLocationsInRegion(LatLngBounds region) {
        plugShareDataSource.getLocationsInRegion(region,
                        QUERY_COUNT,
                        new ServiceCallback<List<PSLocation>>() {
                            @Override
                            public void onSuccess(List<PSLocation> response) {
                                mapView.addMarkersForLocations(response, false);
                            }

                            @Override
                            public void onFailure() {
                                Log.i(TAG, "loadLocationsInRegion onFailure: ");
                            }
                        }
                );
    }
}
