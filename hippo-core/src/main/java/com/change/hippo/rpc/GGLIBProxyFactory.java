package com.change.hippo.rpc;

import com.change.hippo.rpc.impl.HelloServiceImpl;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * User: change.long
 * Date: 16/4/11
 * Time: 上午12:24
 */
public class GGLIBProxyFactory implements MethodInterceptor {

    private Object target;

    public GGLIBProxyFactory(Object target) {
        this.target = target;
    }

    public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl();
        GGLIBProxyFactory proxyFactory = new GGLIBProxyFactory(helloService);
        HelloService helloServiceProxy = proxyFactory.createGGLIBProxy();

        int c = 10;
        while (c-- > -1) {
            HelloService.Person hello = helloServiceProxy.save("hello");
            System.out.println(hello);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T createGGLIBProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    public Object intercept(Object o,
                            Method method,
                            Object[] objects,
                            MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(target,objects);
    }
}
