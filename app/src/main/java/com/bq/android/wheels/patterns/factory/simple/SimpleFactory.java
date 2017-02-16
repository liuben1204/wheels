package com.bq.android.wheels.patterns.factory.simple;

/**
 * Created by liuben on 17/2/16.
 */

public class SimpleFactory {
    private static final String TAG = "SimpleFactory";

    private static final String PRODUCT_SIMPLE = "product_simple";
    private static final String PRODUCT_COMPLEX = "product_complex";


    public IProduct produce(String flag) {
        IProduct product = null;
        switch (flag) {
            case PRODUCT_SIMPLE:
                product = new ProductSimple();
                break;
            case PRODUCT_COMPLEX:
                product = new ProductComplex();
                break;
            default:
                break;
        }
        return product;
    }
}
