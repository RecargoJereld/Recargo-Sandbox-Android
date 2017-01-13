package com.recargo.recargosandbox.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.BaseDataSource;
import com.recargo.recargosandbox.data.api.model.PSLocation;
import com.recargo.recargosandbox.ui.map.MapContract;
import com.recargo.recargosandbox.ui.map.MapPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by jereld on 1/12/17.
 */

public class MapPresenterTest {
    private static final LatLngBounds LAT_LNG_BOUNDS =
            new LatLngBounds(new LatLng(10, 10), new LatLng(10, 10));

    private List<PSLocation> psLocations;

    @Mock
    MapContract.View mapView;

    @Mock
    BaseDataSource plugShareDataSource;

    @Captor
    private ArgumentCaptor<ServiceCallback<List<PSLocation>>> loadLocationsCaptor;

    private MapPresenter mapPresenter;

    @Before
    public void setupMapPresenter() {
        MockitoAnnotations.initMocks(this);

        setupPSLocations();
        mapPresenter = new MapPresenter(mapView, plugShareDataSource);
    }

    private void setupPSLocations() {
        PSLocation psLocation = new PSLocation();
        psLocation.name = "Recargo Research and Development";
        psLocation.latitude = 33.992274;
        psLocation.longitude = -118.472124;

        psLocations = new ArrayList<>();
        psLocations.add(psLocation);
    }

    @Test
    public void loadLocationsInRegionAndLoadIntoMap() {
        mapPresenter.loadLocationsInRegion(LAT_LNG_BOUNDS);

        verify(plugShareDataSource).getLocationsInRegion(eq(LAT_LNG_BOUNDS),
                eq(MapPresenter.QUERY_COUNT),
                loadLocationsCaptor.capture());
        loadLocationsCaptor.getValue().onSuccess(psLocations);

        verify(mapView).addMarkersForLocations(psLocations, false);
    }
}
