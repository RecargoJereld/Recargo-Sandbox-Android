package com.recargo.recargosandbox.ui.map;

import com.recargo.recargosandbox.data.api.BaseDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/5/17.
 */

@Module
public class MapPresenterModule {
    private final MapContract.View mapView;
    private final BaseDataSource plugShareDataSource;

    MapPresenterModule(MapContract.View mapView, BaseDataSource plugShareDataSource) {
        this.mapView = mapView;
        this.plugShareDataSource = plugShareDataSource;
    }

    @Provides
    MapContract.View provideMapView() {
        return mapView;
    }

    @Provides
    BaseDataSource providePlugShareDataSource() {
        return plugShareDataSource;
    }
}
