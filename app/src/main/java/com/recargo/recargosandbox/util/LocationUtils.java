package com.recargo.recargosandbox.util;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by jereld on 1/10/17.
 */

public class LocationUtils {
    public static LatLng locationToLatLng(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
