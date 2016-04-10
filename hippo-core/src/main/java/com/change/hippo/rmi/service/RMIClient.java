package com.change.hippo.rmi.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午5:33
 */
public class RMIClient {


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        String url = "rmi://localhost:1099/com.change.hippo.service.impl.HelloServiceImpl";
        HelloService helloService = (HelloService) Naming.lookup(url);
        String result = helloService.save("change");
        System.out.println(result);
    }

}
