package com.change.hippo.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: change.long
 * Date: 2017/9/15
 * Time: 上午12:24
 */
public class WeightRoundRobin {
    private static Integer pos = 0;
    public static void main(String[] args) {
        for (int i = 0, len = 20; i < len; i++) {
            String server = getServer();
            System.out.println(server);
        }
    }

    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set keySet = serverMap.keySet();
        Iterator iterator = keySet.iterator();

        List serverList = new ArrayList();
        while (iterator.hasNext())
        {
            String server = (String) iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++)
                serverList.add(server);
        }

        String server = null;
        synchronized (pos)
        {
            if (pos > keySet.size())
                pos = 0;
            server = (String) serverList.get(pos);
            pos ++;
        }

        return server;
    }
}
