package com.bq.android.wheels;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public int getUid() {
        int uid = 0;
        try {
            PackageManager packageManager = mWheelsApplication.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.bq.android.wheels",
                    PackageManager.GET_ACTIVITIES);
            uid = applicationInfo.uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return uid;
    }
}
