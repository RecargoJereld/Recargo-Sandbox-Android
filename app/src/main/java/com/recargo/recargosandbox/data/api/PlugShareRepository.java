package com.recargo.recargosandbox.data.api;

import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jereld on 1/19/17.
 */

@Singleton
public class PlugShareRepository implements BaseDataSource {

    private final BaseDataSource localPlugShareDataSource;
    private final BaseDataSource remotePlugShareDataSource;

    @Inject
    public PlugShareRepository(@Local BaseDataSource localPlugShareDataSource,
                               @Remote BaseDataSource remotePlugShareDataSource) {
        this.localPlugShareDataSource = localPlugShareDataSource;
        this.remotePlugShareDataSource = remotePlugShareDataSource;
    }

    @Override
    public void getLocationsInRegion(LatLngBounds mapBounds,
                                     int count,
                                     ServiceCallback<List<PSLocation>> callback) {
        remotePlugShareDataSource.getLocationsInRegion(mapBounds, count,
                new ServiceCallback<List<PSLocation>>() {
                    @Override
                    public void onSuccess(List<PSLocation> response) {
                        writeLocationsToLocalCache(response);
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onFailure() {
                        callback.onFailure();
                    }
                });
    }

    @Override
    public void addLocation(PSLocation location) {
        // TODO: 1/19/17 add location to backend and local cache
    }

    private void writeLocationsToLocalCache(List<PSLocation> psLocations) {
        for (PSLocation psLocation :
                psLocations) {
            localPlugShareDataSource.addLocation(psLocation);
        }
    }
}
