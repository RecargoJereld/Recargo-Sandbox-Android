package com.recargo.recargosandbox;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/5/17.
 */

@Module
public final class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
