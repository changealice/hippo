package com.change.hippo.rpc.impl;

import com.change.hippo.rpc.HelloService;
import com.change.hippo.rpc.annotation.RPCService;

import java.util.concurrent.ThreadLocalRandom;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午8:07
 */
@RPCService(HelloService.class)
public class HelloServiceImpl implements HelloService {


    public Person save(String name) {
        Person person = new Person();
        person.name = name;
        person.password = ThreadLocalRandom.current().nextInt(8) + "";
        return person;
    }
}
