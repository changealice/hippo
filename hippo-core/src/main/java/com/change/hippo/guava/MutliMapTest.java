package com.change.hippo.guava;

import com.google.common.collect.ArrayListMultimap;

/**
 * 一键多值的Map
 */
public class MutliMapTest {

    public static void main(String[] args) {
        final ArrayListMultimap<Object, Object> multimap = ArrayListMultimap.create();

        multimap.put("zhangsan", "1");
        multimap.put("zhangsan", "2");
        multimap.put("zhangsan", "3");
        multimap.put("zhangsan", "4");

        multimap.put("lisi", "11");
        multimap.put("lisi", "12");
        multimap.put("lisi", "13");
        multimap.put("lisi", "14");

        for (Object o : multimap.get("zhangsan")) {
            System.out.println(o);
        }
        System.out.println();
        for (Object o : multimap.get("lisi")) {
            System.out.println(o);
        }

    }
}
