package com.recargo.recargosandbox;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/11/17.
 */

@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(RecargoSandboxApp app) {
        context = app.getApplicationContext();
    }

    @Provides
    @Named("Application Context")
    Context provideApplicationContext() {
        return context;
    }
}
