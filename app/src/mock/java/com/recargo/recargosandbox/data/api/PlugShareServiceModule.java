package com.recargo.recargosandbox.data.api;

import com.recargo.recargosandbox.util.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/12/17.
 */

@Module(includes = PlugShareApiServiceModule.class)
public class PlugShareServiceModule {
    @Provides
    @ApplicationScope
    BaseDataSource providePlugShareDataSource(PlugShareApiService plugShareApiService) {
        return new FakePlugShareDataSource();
    }
}