package com.recargo.recargosandbox.data.api.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLngBounds;
import com.recargo.recargosandbox.data.ServiceCallback;
import com.recargo.recargosandbox.data.api.BaseDataSource;
import com.recargo.recargosandbox.data.api.local.PlugShareContract.LocationEntry;
import com.recargo.recargosandbox.data.api.model.PSLocation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

/**
 * Created by jereld on 1/19/17.
 */

@Singleton
public class LocalPlugShareDataSource implements BaseDataSource {

    private PlugShareDbHelper dbHelper;

    public LocalPlugShareDataSource(Context context) {
        dbHelper = new PlugShareDbHelper(context);
    }

    @Override
    public void getLocationsInRegion(LatLngBounds mapBounds,
                                     int count,
                                     ServiceCallback<List<PSLocation>> callback) {
        // return an empty list for now
        callback.onSuccess(new ArrayList<>());
    }

    public void addLocation(PSLocation psLocation) {
        if (locationExists(psLocation)) {
            updateLocationInLocalCache(psLocation);
        } else {
            insertLocationInLocalCache(psLocation);
        }
    }

    private boolean locationExists(PSLocation psLocation) {
        SQLiteDatabase locationsDb = dbHelper.getReadableDatabase();

        String[] projection = {PlugShareContract.LocationEntry._ID};
        String selection = PlugShareContract.LocationEntry._ID + " = ? ";
        String[] selectionArgs = {String.valueOf(psLocation.id)};

        Cursor cursor = locationsDb.query(PlugShareContract.LocationEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int rowCount = cursor.getCount();
        cursor.close();

        return rowCount > 0;
    }

    private void insertLocationInLocalCache(PSLocation psLocation) {
        SQLiteDatabase locationsDb = dbHelper.getWritableDatabase();

        ContentValues locationValues = makeContentValues(psLocation);
        locationsDb.insert(PlugShareContract.LocationEntry.TABLE_NAME, null, locationValues);
    }

    private void updateLocationInLocalCache(PSLocation psLocation) {
        SQLiteDatabase locationsDb = dbHelper.getWritableDatabase();

        ContentValues locationValues = makeContentValues(psLocation);

        String whereClause = PlugShareContract.LocationEntry._ID + " = ? ";
        String[] whereArgs = {String.valueOf(psLocation.id)};

        locationsDb.update(PlugShareContract.LocationEntry.TABLE_NAME,
                locationValues,
                whereClause,
                whereArgs);
    }

    private ContentValues makeContentValues(PSLocation psLocation) {
        ContentValues locationValues = new ContentValues();

        locationValues.put(LocationEntry._ID, psLocation.id);
        locationValues.put(LocationEntry.COLUMN_LOCATION_NAME, psLocation.name);
        locationValues.put(LocationEntry.COLUMN_LOCATION_LATITUDE, psLocation.latitude);
        locationValues.put(LocationEntry.COLUMN_LOCATION_LONGITUDE, psLocation.longitude);

        return locationValues;
    }
}
