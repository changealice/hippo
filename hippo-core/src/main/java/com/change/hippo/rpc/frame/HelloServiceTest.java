package com.change.hippo.rpc.frame;

import com.change.hippo.rpc.HelloService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.util.Enumeration;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午9:26
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-rpc-consumer.xml")
public class HelloServiceTest {

//    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void helloTest() {
        HelloService helloService = rpcProxy.create(HelloService.class);
        int c = 100000;
        while (--c > -1) {
            long start = System.currentTimeMillis();
            HelloService.Person person = helloService.save("Hello! World");
            System.out.println(c + " " + (System.currentTimeMillis() - start));
            System.out.println(person);
            Assert.assertEquals("Hello! World", person.name);
        }
    }

    @Test
    public void testGetClassByPackage() throws Exception {

        String packageName = "com.change.hippo".replace(".","/");
        Enumeration<URL> resources = HelloServiceTest.class.getClassLoader().getResources(packageName);
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            System.out.println(url);
        }
    }
}
