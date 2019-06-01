package com.change.hippo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda {

    private static final Logger LOGGER = LoggerFactory.getLogger(Lambda.class);

    @Test
    public void testForEachMap() {
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);


//        for (Map.Entry<String, Integer> entry : items.entrySet()) {
//            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
//        }

        //遍历
        items.forEach((k, v) -> {
            System.out.println("Item : " + k + " Count : " + v);
        });
    }

    @Test
    public void testForEachList() {
//        List<String> items = new ArrayList<>();
//        items.add("A");
//        items.add("B");
//        items.add("C");
//        items.add("D");
//        items.add("E");
//        items.add("E");
//        items.add("F");
//        items.add(null);
//        items.add(" ");

        //遍历
//        items.forEach(s -> {
//            System.out.println("Item : " + s);
//        });

        //遍历
//        items.forEach(System.out::println);

        //遍历： Stream and filter 先转换成stream,再filter，再遍历
        //items.stream().filter(s -> s.equals("A") || s.equals("B")).forEach(System.out::println);

//        List<String> list = items.stream().
//                filter(s -> s.equals("E")).
//                collect(Collectors.toList());
//
//
//        list.forEach(System.out::println);

//        String result = items.stream() // Convert to steam
//                .filter(s -> s.contains("E"))// we want "E" only
//                .findAny() //If 'findAny' then return found
//                .orElse(null);//If not found, return null
//
//        System.out.println(result);
//
//
//        String result2 = items.stream() // Convert to steam
//                .filter(s -> s.contains("F") && s.charAt(0) == 70)// we want "E" only,multiple condition
//                .findAny() //If 'findAny' then return found
//                .orElse(null);//If not found, return null
//
//        System.out.println(result2);
//
//        List<String> result3 = items.stream()
//                .map(String::toLowerCase)
//                .collect(Collectors.toList());
//
//        System.out.println(result3);

//        List<Integer> result4 = items.stream()
//                .map(s -> s.charAt(0) * 2)
//                .collect(Collectors.toList());
//        System.out.println(result4);

//
//        Map<String, Long> result5 = items.stream()
//                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));
//
//        System.out.println(result5);
//
//        items.stream()
//                .filter(StringUtils::isNotBlank)
//                .forEach(System.out::println);
//
//
//        Stream<String> language = Stream.of("java", "python", "node");
        // use log4j logging
//        language.filter(s -> LOGGER.isDebugEnabled()).collect(Collectors.toList())
//                .forEach(LOGGER::info);

        //use system.err
//        language.collect(Collectors.toList()).forEach(System.err::println);
        //filter java
//        language.filter(x -> x.equalsIgnoreCase("java"))
//                .collect(Collectors.toList())
//                .forEach(System.out::println);
//
//        String[] array = {"a", "b", "c", "d", "e"};
//        Stream<String> stream = Arrays.stream(array);
//
//        stream.filter(x -> x.equalsIgnoreCase("a"))
//                .collect(Collectors.toList())
//                .forEach(System.out::println);


        //Reuse a stream
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("java", "python", "node", "node", null);

        streamSupplier.get()
                .filter("node"::equalsIgnoreCase)
                .collect(Collectors.toList())
                .forEach(System.out::println);


        System.out.println(StringUtils.repeat('=', 20));

        streamSupplier.get()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println(StringUtils.repeat('=', 20));
        long count = streamSupplier.get()
                .filter(Objects::nonNull)
                .count();

//        System.out.println("count=" + count);


        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);


        LinkedHashMap<String, Integer> map = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


//        System.out.println(map);

        Map<String, String> result1 = streamSupplier
                .get()
                .filter(StringUtils::isNotBlank)//filter blank value
                .collect(Collectors.toMap(      // collector to map
                        String::trim, String::trim, // key=value,value =value return string.trim
                        (oldValue, newValue) -> newValue,  // if same key,return new value
                        LinkedHashMap::new //return a LinkedHashMap  keep order
                ));

//        System.out.println(result1);

//        unsortMap.entrySet().stream()
//                .filter(x -> 9 == (x.getValue())).forEach(System.out::println);

        Map<String, Integer> result2 = unsortMap.entrySet().stream()
                .filter(x -> {
                    String key = x.getKey();
                    return "b".equalsIgnoreCase(key) || "z".equalsIgnoreCase(key);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(result2);


        result2 = unsortMap.entrySet().stream()
                .filter(x -> {
                    Integer value = x.getValue();
                    return 50 == value || 99 == value;
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        System.out.println(result2);

        System.out.println(StringUtils.repeat('=', 20));
        System.out.println(filterByValue(unsortMap, x -> x == (50) || x == 10));


        System.out.println(StringUtils.repeat('=', 20));
        System.out.println(filterByValue(unsortMap, x -> x == 10));


        System.out.println(StringUtils.repeat('=', 20));

        List<String> list = unsortMap.keySet()
                .stream()
                .collect(Collectors.toList());

        System.out.println(list);

        System.out.println(StringUtils.repeat('=', 20));

        List<Integer> list1 = unsortMap.values()
                .stream()
                .collect(Collectors.toList());
        System.out.println(list1);

        System.out.println(StringUtils.repeat('=', 20));
        String result = streamSupplier.get()
                .collect(Collectors.joining("|", "{", "}"));
        System.out.println(result);

        System.out.println(StringUtils.repeat('=', 20));


        try (Stream<String> stream = Files.lines(Paths.get("/usr/local/applogs/authenticate/1.txt"))) {
            stream.filter(line -> line.startsWith("\"GC task"))
                    .map(String::toUpperCase)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "\"GC TASK";

        str.chars()
                .mapToObj(x -> (char) x)
                .forEach(System.out::println);


        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));

        System.out.println(Duration.ofHours(1).getSeconds());
    }


    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet().stream()
                .filter(x -> predicate.test(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
