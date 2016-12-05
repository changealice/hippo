package com.change.spi;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/4
 * Time: 下午11:09
 */
public class OracleImpl implements Spi {
    @Override
    public boolean support(String name) {
        return "oracle".equalsIgnoreCase(name.trim());
    }

    @Override
    public String sayHello() {
        return "oracle";
    }
}
