package com.change.hippo;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.bill99.thunder.config.ReferenceConfig;
import com.bill99.thunder.serializer.JDKSerializer;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * User: change.long
 * Date: 16/6/27
 * Time: 下午3:08
 */
public class CuratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorTests.class);

    private final static String ZK_ADDRESS = "192.168.65.196:2181";

    public static void main(String[] args) throws Exception {
        CuratorUtil curatorUtil = new CuratorUtil(ZK_ADDRESS);
        byte[] bytes = curatorUtil.getClient().getData().forPath("/Thunder/reference/app-ma-mgw-auth/com.bill99.seashell.domain.paymentsetting.service.PaymentTypeSettingSvc");

        ReferenceConfig deserialize = JDKSerializer.deserialize(bytes);
        System.out.println("ReferenceConfig=" + JSON.toJSONString(deserialize));
//        curatorUtil.createNode("/change/test1", "test1");
//        curatorUtil.createNode("/change/test1/test1", "/change/test/test1");
//
//        curatorUtil.updateNode("/change/test1", "test3Updated");
//
//
//        List<String> list = curatorUtil.listChildren("/change");
//        list.forEach(System.out::println);
//
//
//        Map<String, String> map = curatorUtil.listChildrenDetail("/change");
//
//        map.entrySet().forEach(entry -> System.out.println(entry.getKey() + "=>" + entry.getValue()));
//
////        curatorUtil.deleteNode("/change");
//
//        curatorUtil.addWatch("/change", true);
//
//        curatorUtil.updateNode("/change/test1", "test3Updated");
//
//        TimeUnit.SECONDS.sleep(600);
        curatorUtil.destory();
    }

    private static class CuratorUtil {
        private final String zkAddress;
        private CuratorFramework client;

        public CuratorUtil(String zkAddress) throws InterruptedException {
            this.zkAddress = zkAddress;
            client = CuratorFrameworkFactory
                    .newClient(zkAddress, new ExponentialBackoffRetry(1000, 3));
            client.getCuratorListenable().addListener(new NodeEventListener());
            client.start();
            client.blockUntilConnected();
        }

        public String getZkAddress() {
            return zkAddress;
        }

        public CuratorFramework getClient() {
            return client;
        }

        public boolean createNode(String nodeName, String value) {
            boolean success = false;
            try {
                Stat stat = client.checkExists().forPath(nodeName);
                if (stat == null) {
                    String opResult;
                    if (Strings.isNullOrEmpty(value)) {
                        opResult = client.create().creatingParentsIfNeeded().forPath(nodeName);
                    } else {
                        opResult = client.create().creatingParentsIfNeeded()
                                .forPath(nodeName, value.getBytes("UTF-8"));
                    }
                    success = Objects.equals(nodeName, opResult);
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return success;
        }

        public boolean updateNode(String nodeName, String updateValue) {
            boolean success = false;
            try {
                Stat stat = client.checkExists().forPath(nodeName);
                if (stat != null) {
                    Stat opResult = client.setData().forPath(nodeName, updateValue.getBytes("UTF-8"));
                    success = opResult != null;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return success;
        }

        public void deleteNode(String nodeName) {
            try {
                client.delete().deletingChildrenIfNeeded().forPath(nodeName);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }

        }

        public List<String> listChildren(String nodeName) {
            List<String> children = Lists.newArrayList();
            try {
                children = client.getChildren().forPath(nodeName);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return children;
        }

        public Map<String, String> listChildrenDetail(String nodeName) {
            Map<String, String> map = Maps.newHashMap();
            List<String> children = listChildren(nodeName);
            GetDataBuilder dataBuilder = client.getData();
            Preconditions.checkArgument(children != null && children.size() > 0);
            for (String child : children) {
                String concatPath = ZKPaths.makePath(nodeName, child);
                try {
                    map.put(child, new String(dataBuilder.forPath(concatPath), "UTF-8"));
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            return map;
        }

        /**
         * 销毁资源
         */
        public void destory() {
            if (client != null) {
                client.close();
            }
        }

        public void addWatch(String nodeName, boolean isSelf) throws Exception {
            if (isSelf) {
                client.getData().watched().forPath(nodeName);
            } else {
                client.getChildren().watched().forPath(nodeName);
            }
        }

        public void addWatch(String nodeName, boolean isSelf, Watcher watcher) throws Exception {
            if (isSelf) {
                client.getData().usingWatcher(watcher).forPath(nodeName);
            } else {
                client.getChildren().usingWatcher(watcher).forPath(nodeName);
            }
        }

        public void addWatch(String nodeName, boolean isSelf, CuratorWatcher watcher) throws Exception {
            if (isSelf) {
                client.getData().usingWatcher(watcher).forPath(nodeName);
            } else {
                client.getChildren().usingWatcher(watcher).forPath(nodeName);
            }
        }

        private class NodeEventListener implements org.apache.curator.framework.api.CuratorListener {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("eventReceived :" + event.toString() + "!@.......................");

                WatchedEvent watchedEvent = event.getWatchedEvent();
                if (watchedEvent != null) {
                    System.out.println(watchedEvent.getState() + "!@============" + watchedEvent.getType());
                    if (watchedEvent.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        switch (watchedEvent.getType()) {
                            case NodeChildrenChanged:
                                // TODO
                                break;
                            case NodeDataChanged:
                                // TODO
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
