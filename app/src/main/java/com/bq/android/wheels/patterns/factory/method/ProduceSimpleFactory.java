package com.bq.android.wheels.patterns.factory.method;

/**
 * Created by liuben on 17/2/16.
 */

public class ProduceSimpleFactory implements IFactory {

    @Override
    public IProduct produce() {
        return new ProductSimple();
    }
}
