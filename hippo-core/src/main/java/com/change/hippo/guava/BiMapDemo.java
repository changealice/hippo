package com.change.hippo.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * 双向MAP。
 */
public class BiMapDemo {

    public static void main(String[] args) {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("change", "jim");

        System.out.println(biMap.get("change"));
        System.out.println(biMap.inverse().get("jim"));
    }
}
