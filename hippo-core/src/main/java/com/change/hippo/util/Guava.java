package com.change.hippo.util;

import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.google.common.util.concurrent.RateLimiter;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * User: change.long@99bill.com Date: 16/6/21
 */
public class Guava {


    @Test
    public void testIteratorStartWithApply() {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana");

        Predicate<String> condition = item -> item.startsWith("P");

        boolean allIsStartsWithP = Iterators.all(list.iterator(), condition);
        System.out.println("all result == " + allIsStartsWithP);
    }

    @Test
    public void testIteratorFilter() throws Exception {
        List<String> list = Lists.newArrayList("Apple", "Pear", "Peach", "Banana", "");
        Iterator<String> startPElements = Iterators.filter(list.iterator(),
                item -> null != item && !"".equals(item) && item.startsWith("P"));
        String listString = Iterators.toString(startPElements);
        System.out.println("all filter result == " + listString);
    }

    @Test
    public void testCache() throws Exception {
        final class Student {
            private long id;
            private String name;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        LoadingCache<Integer, Student> studentCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .expireAfterWrite(8, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(100)
                .recordStats()
                .removalListener(notification ->
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause()))
                .build(new CacheLoader<Integer, Student>() {
                    @Override
                    public Student load(Integer key) throws Exception {
                        System.out.println("load student " + key);
                        Student student = new Student();
                        student.setId(key);
                        student.setName("name" + key);
                        return student;
                    }
                });

        for (int i = 0, len = 100; i < len; i++) {
            Student student = studentCache.get(1);
            System.out.println(student);
            TimeUnit.SECONDS.sleep(1);
        }


        System.out.println("cache stats:");
        System.out.println(studentCache.stats().toString());

    }

    @Test
    public void testIO() throws Exception {
        String filename = "/Users/changealice/change.txt";
        System.out.println(Files.getFileExtension(filename));
        System.out.println(Files.getNameWithoutExtension(filename));
        String content = "changejava\nchange\njava";
        File to = new File(filename);
        Files.write(content.getBytes(), to);

        List<String> lines = Files.readLines(to, Charsets.UTF_8);
        for (int i = 0, len = lines.size(); i < len; i++) {
            System.out.println(lines.get(i));
        }

        LineProcessor<Integer> counter = new LineProcessor<Integer>() {
            private int rowNum = 0;

            @Override
            public boolean processLine(String line) throws IOException {
                rowNum++;
                return true;
            }

            @Override
            public Integer getResult() {
                return rowNum;
            }
        };
        Files.readLines(to, Charsets.UTF_8, counter);
        System.out.println(counter.getResult());
    }

    @Test
    public void testRateLimiter() throws Exception {

        ExecutorService executors = Executors.newFixedThreadPool(5);
        RateLimiter rateLimiter = RateLimiter.create(2);
        rateLimiter.acquire();
        Future<String> future = executors.submit(() -> "123");

        System.out.println(future.get());

    }
}
