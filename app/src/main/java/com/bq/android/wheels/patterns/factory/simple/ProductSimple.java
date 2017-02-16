package com.bq.android.wheels.patterns.factory.simple;

import android.util.Log;

/**
 * Created by liuben on 17/2/16.
 */

public class ProductSimple implements IProduct {
    private static final String TAG = "ProductSimple";

    @Override
    public void produce() {
        Log.i(TAG, "ProductSimple produce simple product");
    }
}
