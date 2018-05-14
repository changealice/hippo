package com.change.hippo.loadbalancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * User: change.long
 * Date: 2017/9/15
 * Time: 上午12:15
 */
public class Random {

    public static void main(String[] args) {
        for (int i = 0, len = 10; i < len; i++) {
            System.out.println(getServer());
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
        ArrayList keyList = new ArrayList();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());

        return (String) keyList.get(randomPos);
    }
}
