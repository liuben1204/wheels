package com.bq.android.wheels.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuben on 17-2-13.
 */

public class WheelsDbHelper extends SQLiteOpenHelper {
    private static volatile WheelsDbHelper mWheelsDbHelper = null;

    private static final String DATABASE_NAME = "wheels.db";
    private static final int DATABASE_VERSION = 1;

    private WheelsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static WheelsDbHelper getInstance(Context context) {
        if (mWheelsDbHelper == null) {
            synchronized (WheelsDbHelper.class) {
                if (mWheelsDbHelper == null) {
                    mWheelsDbHelper = new WheelsDbHelper(context);
                }
            }
        }
        return mWheelsDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Entries.UserInfoEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
