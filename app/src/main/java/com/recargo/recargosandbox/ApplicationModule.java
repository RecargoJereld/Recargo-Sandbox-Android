package com.recargo.recargosandbox;

import android.content.Context;

import com.recargo.recargosandbox.util.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/11/17.
 */

@ApplicationScope
@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(RecargoSandboxApp app) {
        context = app.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    Context provideApplicationContext() {
        return context;
    }
}
