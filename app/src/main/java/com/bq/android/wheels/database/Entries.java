package com.bq.android.wheels.database;

import android.provider.BaseColumns;

/**
 * Created by liuben on 17-2-13.
 */

public final class Entries {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";

    private Entries() {
    }

    public static class UserInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_USER_NAME = "user_name";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP +
                        COLUMN_USER_NAME + TEXT_TYPE + " )";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public static class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "number";
        public static final String COLUMN_ADDRESS = "address";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE" + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_ADDRESS + TEXT_TYPE + ")";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS" + TABLE_NAME;

    }

}
