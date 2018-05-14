package com.change.hippo.spring;

/**
 * User: change.long
 * Date: 2017/10/8
 * Time: 下午4:52
 */
@RoutingSwitch("hello.switch")
public interface HelloService {

    @RoutingSwitch("A")
    void sayHello();

    void sayHi();
}
