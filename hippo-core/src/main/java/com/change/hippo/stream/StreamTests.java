package com.change.hippo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: change.long
 * Date: 16/6/22
 * Time: 下午5:26
 */
public class StreamTests {


    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1, 2, 5, 2);
        l.stream()
                .map(Integer::new)
                .filter(e -> e > 3)//过来大于3
                .distinct()//去重复
                .sorted()//排序
                .collect(Collectors.toList()).forEach(System.out::println);//收集到list返回

        l.parallelStream().map(Object::hashCode)
                .distinct().sorted().collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("reduceAvg User Age: " + l.parallelStream().mapToInt(value -> value).average().getAsDouble());
        System.out.println("reduceSum User Age: " + l.parallelStream().mapToInt(value -> value).reduce(0, (x, y) -> x * y));

    }

}
