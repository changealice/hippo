package com.change.spi;

import java.util.ServiceLoader;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/4
 * Time: 下午11:14
 */
public class SpiFactory {

    private static ServiceLoader<Spi> spiLoader = ServiceLoader.load(Spi.class);


    public static Spi getSpi(String name) {
        for (Spi spi : spiLoader) {
            if (spi.support(name)) {
                return spi;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Spi spi = SpiFactory.getSpi("oracle");
        if (null != spi) {
            System.out.println(spi.sayHello());
        }
    }
}
