package com.recargo.recargosandbox.data.api;

import android.content.Context;

import com.recargo.recargosandbox.data.api.local.LocalPlugShareDataSource;
import com.recargo.recargosandbox.data.api.remote.PlugShareApiService;
import com.recargo.recargosandbox.data.api.remote.PlugShareApiServiceModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/12/17.
 */

@Singleton
@Module(includes = PlugShareApiServiceModule.class)
public class PlugShareServiceModule {
    @Provides
    @Local
    BaseDataSource provideLocalPlugShareDataSource(@Named("Application Context") Context context) {
        return new LocalPlugShareDataSource(context);
    }

    @Provides
    @Remote
    BaseDataSource providePlugShareDataSource(PlugShareApiService plugShareApiService) {
        return new FakePlugShareDataSource();
    }
}
