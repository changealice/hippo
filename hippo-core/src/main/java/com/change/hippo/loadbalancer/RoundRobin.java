package com.change.hippo.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: change.long
 * Date: 2017/9/15
 * Time: 上午12:11
 */
public class RoundRobin {

    private static Integer pos = 0;

    public static void main(String[] args) {
        for (int i = 0, len = 20; i < len; i++) {
            String server = getServer();
            System.out.println(server);
        }
    }

    /**
     * 轮询调度算法的原理是每一次把来自用户的请求轮流分配给内部中的服务器，从1开始，直到N(内部服务器个数)，然后重新开始循环。算法的优点是其简洁性，它无需记录当前所有连接的状态，所以它是一种无状态调度
     * @return
     */
    public static String getServer() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set keySet = serverMap.keySet();
        ArrayList keyList = new ArrayList();
        keyList.addAll(keySet);

        String server;
        synchronized (pos) {
            if (pos >= keySet.size())
                pos = 0;
            server = (String) keyList.get(pos);
            pos++;
        }

        return server;
    }
}
