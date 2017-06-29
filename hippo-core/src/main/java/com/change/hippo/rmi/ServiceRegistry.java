package com.change.hippo.rmi;

import com.change.hippo.rmi.service.impl.HelloServiceImpl;
import com.change.hippo.rpc.frame.Constants;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午5:41
 * 注册服务到zookeeper中，
 */
public class ServiceRegistry {


    public final Logger logger = LoggerFactory.getLogger(ServiceRegistry.class);
    private final String registryAddress;

    private CountDownLatch latch = new CountDownLatch(1);


    ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public static void main(String[] args) throws RemoteException {
        if (args.length != 2) {
            System.err.println("please using command: java Server <rmi_host> <rmi_port>");
            System.exit(-1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        ServiceRegistry serviceProvider = new ServiceRegistry("127.0.0.1");
        HelloServiceImpl helloService = new HelloServiceImpl();
        serviceProvider.publish(helloService, host, port);
    }

    /**
     * 发布rmiservice同时注册到zookeeper中
     * @param remote rmi接口类
     * @param host ip地址
     * @param port 端口1-65535
     */
    public void publish(Remote remote, String host, int port) {
        String url = publishService(remote, host, port);
        if (null != url) {
            ZooKeeper zk = connectZkServer();
            if (null != zk) {
                createNode(zk, url);
            }
        }

    }

    private void createNode(ZooKeeper zk, String url) {
        try {
            // 创建一个临时性且有序的 ZNode
            String path = zk.create(Constants.ZK_DATA_PATH, url.getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.info("create zookeeper node ({}=>{})", path, url);
        } catch (KeeperException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }


    }

    private ZooKeeper connectZkServer() {
        ZooKeeper zk = null;
        try {

            zk = new ZooKeeper(registryAddress, Constants.TIMEOUT, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    //如果连接事件
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        return zk;
    }

    private String publishService(Remote remote, String host, int port) {
        try {
            String url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
            LocateRegistry.createRegistry(port);
            Naming.rebind(url, remote);
            return url;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectZkServer();
            if (zk != null) {
                createNode(zk, data);
            }
        }
    }
}
