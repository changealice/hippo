package com.change.hippo.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午5:16
 */
public interface HelloService extends Remote {


    String save(String name) throws RemoteException;

}
