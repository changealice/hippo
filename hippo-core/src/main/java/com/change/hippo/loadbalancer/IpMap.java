package com.change.hippo.loadbalancer;

import java.util.HashMap;

/**
 * User: change.long
 * Date: 2017/9/15
 * Time: 上午12:10
 */
public class IpMap {

    // 待路由的Ip列表，Key代表Ip，Value代表该Ip的权重
    public static HashMap<String, Integer> serverWeightMap =
            new HashMap<String, Integer>();

    static
    {
        serverWeightMap.put("192.168.1.100", 1);
        serverWeightMap.put("192.168.1.101", 1);
        // 权重为4
        serverWeightMap.put("192.168.1.102", 4);
        serverWeightMap.put("192.168.1.103", 1);
        serverWeightMap.put("192.168.1.104", 1);
        // 权重为3
        serverWeightMap.put("192.168.1.105", 3);
        serverWeightMap.put("192.168.1.106", 1);
        // 权重为2
        serverWeightMap.put("192.168.1.107", 2);
        serverWeightMap.put("192.168.1.108", 1);
        serverWeightMap.put("192.168.1.109", 1);
        serverWeightMap.put("192.168.1.110", 1);
    }


    public static void main(String[] args) {
        System.out.println(stringIPToLong("2444.0.0.1"));

        System.out.println(longIPToString(2348810241L));
    }


    public static Long stringIPToLong(String IP)
    {
        String[] addressArray = IP.split("\\.");

        long result = 0;

        for (int i = 0; i < addressArray.length; i++)
        {
            int power = 3 - i;

            result += ((Integer.parseInt(addressArray[i]) % 256 * Math.pow(256, power)));
        }

        return result;
    }

    public static String longIPToString(long IP)
    {
        return ((IP >> 24 ) & 0xFF) + "." + ((IP >> 16 ) & 0xFF) + "." + ((IP >> 8 ) & 0xFF) + "." +( IP & 0xFF);
    }
}
