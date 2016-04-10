package com.change.hippo.rpc.frame;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午9:17
 */
public class RpcBootstrap {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-rpc-provider.xml");

    }
}
