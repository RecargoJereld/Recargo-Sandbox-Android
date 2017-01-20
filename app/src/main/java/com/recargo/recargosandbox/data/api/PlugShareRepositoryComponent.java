package com.recargo.recargosandbox.data.api;

import com.recargo.recargosandbox.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jereld on 1/19/17.
 */

@Singleton
@Component (modules = {PlugShareServiceModule.class, ApplicationModule.class})
public interface PlugShareRepositoryComponent {

    PlugShareRepository getPlugShareRepository();
}
