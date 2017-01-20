package com.recargo.recargosandbox.ui.map;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/5/17.
 */

@Module
public class MapPresenterModule {
    private final MapContract.View mapView;

    MapPresenterModule(MapContract.View mapView) {
        this.mapView = mapView;
    }

    @Provides
    MapContract.View provideMapView() {
        return mapView;
    }
}
