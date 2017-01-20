package com.recargo.recargosandbox.data.api.remote;

import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.BaseDataSource;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jereld on 1/11/17.
 */

public class RemotePlugShareDataSource implements BaseDataSource {
    private static final String TAG = RemotePlugShareDataSource.class.getSimpleName();

    private final PlugShareApiService apiService;

    public RemotePlugShareDataSource(PlugShareApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getLocationsInRegion(LatLngBounds mapBounds,
                                     int count,
                                     ServiceCallback<List<PSLocation>> callback) {
        double latCenter = (mapBounds.northeast.latitude + mapBounds.southwest.latitude) / 2;
        double lngCenter = (mapBounds.northeast.longitude + mapBounds.southwest.longitude) / 2;
        double spanLat = Math.abs(mapBounds.northeast.latitude - mapBounds.southwest.latitude);
        double spanLng = Math.abs(mapBounds.northeast.longitude - mapBounds.southwest.longitude);

        Call<List<PSLocation>> call = apiService.getLocationInRegion(latCenter,
                lngCenter,
                spanLat,
                spanLng,
                count);
        call.enqueue(new Callback<List<PSLocation>>() {
            @Override
            public void onResponse(Call<List<PSLocation>> call,
                                   Response<List<PSLocation>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<PSLocation>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void addLocation(PSLocation location) {
        // TODO: 1/19/17 post location to backend
    }
}
