package com.recargo.recargosandbox.data.api;

import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jereld on 1/12/17.
 */

public class FakePlugShareDataSource implements BaseDataSource {
    @Override
    public void getLocationsInRegion(LatLngBounds mapBounds,
                                     int count,
                                     ServiceCallback<List<PSLocation>> callback) {
        PSLocation psLocation = new PSLocation();
        psLocation.name = "Recargo Research and Development";
        psLocation.latitude = 33.992274;
        psLocation.longitude = -118.472124;

        ArrayList<PSLocation> response = new ArrayList<>();
        response.add(psLocation);

        callback.onSuccess(response);
    }

    @Override
    public void addLocation(PSLocation location) {
        // no-op
    }
}
