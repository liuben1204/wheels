package com.bq.android.wheels.patterns.factory.method;

import android.util.Log;

/**
 * Created by liuben on 17/2/16.
 */

public class ProductSimple implements IProduct {
    private static final String TAG = "ProductSimple";

    @Override
    public void product() {
        Log.i("method", "method factory produce simple product");
    }
}
