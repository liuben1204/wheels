package com.bq.android.wheels.patterns;

/**
 * Created by liuben on 17/2/16.
 */

public class SingleMode {
    private static volatile SingleMode mSingleMode = null;

    private SingleMode() {
    }

    public static SingleMode getInstance() {
        if (mSingleMode == null) {
            synchronized (SingleMode.class) {
                if (mSingleMode == null) {
                    mSingleMode = new SingleMode();
                }
            }
        }
        return mSingleMode;
    }
}
