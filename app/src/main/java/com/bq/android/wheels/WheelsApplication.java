package com.bq.android.wheels;

import android.app.Application;

/**
 * Created by liuben on 17-1-5.
 */

public class WheelsApplication extends Application {
    private static WheelsApplication mWheelsApplication;

    public static WheelsApplication getInstance() {
        return mWheelsApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mWheelsApplication = this;
    }
}
