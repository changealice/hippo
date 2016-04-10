package com.change.hippo.rpc;

import com.change.hippo.rpc.impl.HelloServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * User: change.long
 * Date: 16/4/11
 * Time: 上午12:01
 */
public class JdkProxyFactory<T> implements InvocationHandler {


    //被代理的类
    final T target;

    public JdkProxyFactory(T object) {
        this.target = object;
    }

    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        JdkProxyFactory<HelloService> jdkProxyFactory = new JdkProxyFactory<HelloService>(helloService);
        HelloService helloProxy = jdkProxyFactory.createProxy();
        int c = 10;
        while (--c > -1){
            System.out.println(helloProxy.save("change"));
        }
    }

    @SuppressWarnings("unchecked")
    public T createProxy() {
        Class<?> aClass = target.getClass();
        return (T) Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object retVal = method.invoke(target, args);
        System.out.println("call" + method.getName() + " " + (System.currentTimeMillis() - start));
        return retVal;
    }
}
