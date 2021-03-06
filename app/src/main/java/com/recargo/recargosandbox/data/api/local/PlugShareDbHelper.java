package com.recargo.recargosandbox.data.api.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.recargo.recargosandbox.data.api.local.PlugShareContract.LocationEntry;

/**
 * Created by jereld on 1/18/17.
 */

public class PlugShareDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "locations.db";

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + LocationEntry.TABLE_NAME + " ("
            + LocationEntry._ID + " INTEGER PRIMARY KEY, "
            + LocationEntry.COLUMN_LOCATION_NAME + " TEXT, "
            + LocationEntry.COLUMN_LOCATION_LATITUDE + " FLOAT NOT NULL DEFAULT 0, "
            + LocationEntry.COLUMN_LOCATION_LONGITUDE + " FLOAT NOT NULL DEFAULT 0"
            + ");";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME;

    public PlugShareDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
