package com.recargo.recargosandbox.ui.map;

import com.recargo.recargosandbox.data.api.PlugShareRepositoryComponent;
import com.recargo.recargosandbox.util.FragmentScope;

import dagger.Component;

/**
 * Created by jereld on 1/5/17.
 */
@FragmentScope
@Component(dependencies = PlugShareRepositoryComponent.class, modules = MapPresenterModule.class)
public interface MapComponent {

    void inject(MapFragment mapFragment);
}
