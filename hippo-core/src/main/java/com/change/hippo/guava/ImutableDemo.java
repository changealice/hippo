package com.change.hippo.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;

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

        Predicate<String> condition = input -> input.startsWith("P");
        System.out.println(Iterators.all(of.asList().iterator(), condition));

        String firstElement = Iterators.get(of.iterator(), 0);

        System.out.println(firstElement);

        System.out.println(Iterators.filter(of.iterator(), "B"::equalsIgnoreCase));

    }
}
