package com.recargo.recargosandbox.data.api.remote;

import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jereld on 1/11/17.
 */

public interface PlugShareApiService {
    @GET("locations/region")
    Call<List<PSLocation>> getLocationInRegion(@Query("latitude") double lat,
                                              @Query("longitude") double lng,
                                              @Query("spanLat") double spanLat,
                                              @Query("spanLng") double spanLng,
                                              @Query("count") int count);
}
