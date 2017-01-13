package com.recargo.recargosandbox.ui.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.BasePresenter;
import com.recargo.recargosandbox.BaseView;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.List;

/**
 * Created by jereld on 1/5/17.
 */

public interface MapContract {
    interface View extends BaseView {
        void setGoogleMapView(GoogleMap googleMapView);

        void moveMapToCurrentLocation();

        void addMarkersForLocations(List<PSLocation> psLocationList, boolean removeOldMarkers);
    }

    interface Presenter extends BasePresenter, OnMapReadyCallback {
        void loadLocationsInRegion(LatLngBounds region);
    }
}
