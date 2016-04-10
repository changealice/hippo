package com.change.hippo.rmi.service.impl;

import com.change.hippo.rmi.service.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午5:18
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
    }

    public String save(String name) throws RemoteException {
        return String.format("Hello %s", name);
    }
}
