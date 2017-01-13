package com.recargo.recargosandbox.ui;

import com.recargo.recargosandbox.GooglePlayServicesModule;

import dagger.Component;

/**
 * Created by jereld on 1/5/17.
 */

@Component(modules = GooglePlayServicesModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
