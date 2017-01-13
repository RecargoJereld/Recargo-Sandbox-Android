package com.recargo.recargosandbox.ui.map;

import dagger.Component;

/**
 * Created by jereld on 1/5/17.
 */
@Component(modules = MapPresenterModule.class)
public interface MapComponent {
    void inject(MapFragment mapFragment);
}
