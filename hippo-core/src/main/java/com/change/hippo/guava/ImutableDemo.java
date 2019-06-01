package com.change.hippo.guava;

import com.google.common.collect.ImmutableSet;

/**
 * 不可更改的集合类
 */
public class ImutableDemo {
    public static void main(String[] args) {
        final ImmutableSet<String> build = ImmutableSet.<String>builder()
                .add("A", "B")
                .build();

        for (String o : build) {
            System.out.println(o);
        }

        final ImmutableSet<String> of = ImmutableSet.of("B", "C");
        for (String s : of) {
            System.out.println(s);
        }
    }
}
