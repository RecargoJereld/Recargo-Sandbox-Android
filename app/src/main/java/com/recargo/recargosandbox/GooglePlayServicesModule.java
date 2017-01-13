package com.recargo.recargosandbox;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jereld on 1/5/17.
 */

@Module(includes = ContextModule.class)
public class GooglePlayServicesModule {

    private FragmentActivity fragmentActivity;
    private GoogleApiClient.OnConnectionFailedListener connectionFailedListener;
    private GoogleApiClient.ConnectionCallbacks connectionCallbacks;

    public GooglePlayServicesModule(
            FragmentActivity fragmentActivity,
            GoogleApiClient.OnConnectionFailedListener connectionFailedListener,
            GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.fragmentActivity = fragmentActivity;
        this.connectionFailedListener = connectionFailedListener;
        this.connectionCallbacks = connectionCallbacks;
    }

    @Provides
    GoogleApiClient provideGoogleApiClient(Context context) {
        return new GoogleApiClient.Builder(context)
                .enableAutoManage(fragmentActivity, connectionFailedListener)
                .addConnectionCallbacks(connectionCallbacks)
                .addApi(LocationServices.API)
                .build();
    }
}
