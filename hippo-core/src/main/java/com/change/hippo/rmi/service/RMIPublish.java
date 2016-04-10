package com.change.hippo.rmi.service;

import com.change.hippo.rmi.service.impl.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午5:31
 */
public class RMIPublish {


    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        int port = 1099;
        String url = "rmi://localhost:1099/com.change.hippo.service.impl.HelloServiceImpl";
        LocateRegistry.createRegistry(port);
        Naming.bind(url,new HelloServiceImpl());
    }
}
