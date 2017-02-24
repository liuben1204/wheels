package com.bq.android.wheels.patterns.factory.method;

import android.util.Log;

/**
 * Created by liuben on 17/2/16.
 */

public class Client {

    public static void use() {
        IFactory factorySimple = new ProduceSimpleFactory();
        factorySimple.produce().product();
        IFactory factoryComplex = new ProduceComplexFactory();
        factoryComplex.produce().product();
    }
}
