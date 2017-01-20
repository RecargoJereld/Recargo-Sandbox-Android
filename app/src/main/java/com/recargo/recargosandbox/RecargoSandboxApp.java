package com.recargo.recargosandbox;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;
import com.recargo.recargosandbox.data.api.DaggerPlugShareRepositoryComponent;
import com.recargo.recargosandbox.data.api.PlugShareRepositoryComponent;

/**
 * Created by jereld on 1/4/17.
 */

public class RecargoSandboxApp extends Application {

    public static RecargoSandboxApp get(Activity activity) {
        return (RecargoSandboxApp) activity.getApplication();
    }

    PlugShareRepositoryComponent plugShareRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        plugShareRepositoryComponent =
                DaggerPlugShareRepositoryComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();
    }

    public PlugShareRepositoryComponent getPlugShareRepositoryComponent() {
        return plugShareRepositoryComponent;
    }
}
