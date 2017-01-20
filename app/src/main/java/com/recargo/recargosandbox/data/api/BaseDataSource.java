package com.recargo.recargosandbox.data.api;

import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.List;

/**
 * Created by jereld on 1/11/17.
 */

public interface BaseDataSource {
    void getLocationsInRegion(LatLngBounds mapBounds,
                              int count,
                              ServiceCallback<List<PSLocation>> callback);

    void addLocation(PSLocation location);
}
