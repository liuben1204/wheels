package com.bq.android.wheels.patterns.factory.simple;

import android.util.Log;

/**
 * Created by liuben on 17/2/16.
 */

public class ProductComplex implements IProduct {
    private static final String TAG = "ProductComplex";

    @Override
    public void produce() {
        Log.i(TAG, "ProductComplex produce complex product");
    }
}
