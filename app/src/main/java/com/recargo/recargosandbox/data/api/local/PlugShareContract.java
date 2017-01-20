package com.recargo.recargosandbox.data.api.local;

import android.provider.BaseColumns;

/**
 * Created by jereld on 1/18/17.
 */

public final class PlugShareContract {
    public static class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "locations";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_LOCATION_NAME = "name";
        public static final String COLUMN_LOCATION_LATITUDE = "latitude";
        public static final String COLUMN_LOCATION_LONGITUDE = "longitude";
    }
}
