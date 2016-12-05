package com.change.spi;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/4
 * Time: 下午11:10
 */
public interface Spi {

    public boolean support(String name);

    public String sayHello();
}
