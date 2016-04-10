package com.change.hippo.rpc.frame;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午7:24
 */
public class ServiceDiscovery {
    public final Logger logger = LoggerFactory.getLogger(ServiceDiscovery.class);


    private CountDownLatch latch = new CountDownLatch(1);
    private List<String> urlList = new ArrayList<String>();
    private String registryAddress;

    public ServiceDiscovery(String registryAddress) {
        this.registryAddress = registryAddress;
        ZooKeeper zk = connectZkServer();
        if (null != zk) {
            watchNode(zk);
        }
    }


    private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(Constants.ZK_REGISTRY_PATH, new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zk);
                    }
                }
            });
            List<String> dataList = new ArrayList<String>();
            for (String node : nodeList) {
                byte[] data = zk.getData(Constants.ZK_REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(data));
            }
            logger.info("node data: {}", dataList);
            urlList = dataList; // 更新最新的 RMI 地址
        } catch (KeeperException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

    }

    private ZooKeeper connectZkServer() {
        ZooKeeper zk = null;
        try {
            Watcher watcher = new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            };
            zk = new ZooKeeper(registryAddress, Constants.TIMEOUT, watcher);
            latch.await();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        return zk;
    }

    public String discover() {
        String data = null;
        int size = urlList.size();

        if (size > 0) {
            if (size == 1) {
                data = urlList.get(0);
                logger.info("using only url {}", data);
            } else {
                data = urlList.get(ThreadLocalRandom.current().nextInt(size));
                logger.info("using random url:{}", data);
            }
        }
        return data;
    }
}
