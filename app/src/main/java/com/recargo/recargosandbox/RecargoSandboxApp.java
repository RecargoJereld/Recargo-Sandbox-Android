package com.recargo.recargosandbox;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;
import com.recargo.recargosandbox.data.api.BaseDataSource;

import javax.inject.Inject;

/**
 * Created by jereld on 1/4/17.
 */

public class RecargoSandboxApp extends Application {

    public static RecargoSandboxApp get(Activity activity) {
        return (RecargoSandboxApp) activity.getApplication();
    }

    @Inject
    BaseDataSource plugShareDataSource;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build()
                .inject(this);
    }

    public BaseDataSource getPlugShareDataSource() {
        return plugShareDataSource;
    }
}
