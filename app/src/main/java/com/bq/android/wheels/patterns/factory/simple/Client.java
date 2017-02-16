package com.bq.android.wheels.patterns.factory.simple;

/**
 * Created by liuben on 17/2/16.
 */

public class Client {

    public static void use() {
        SimpleFactory simpleFactory = new SimpleFactory();
        IProduct productSimple = simpleFactory.produce("product_simple");
        productSimple.produce();
        IProduct productComplex = simpleFactory.produce("product_complex");
        productComplex.produce();
    }
}
