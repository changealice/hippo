package com.change.hippo;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * User: change.long
 * Date: 16/6/27
 * Time: 下午2:02
 */
public class RateLimiterTests {

    public static void main(String[] args) {
        testRateLimiter();
        testListenableFuture();
        testJavaSemaphore();
    }

    private static void testJavaSemaphore() {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semp = new Semaphore(5);
        //模拟50个用户
        for (int i = 0, len = 50; i < len; i++) {
            final int NO = i;
            exec.execute(() -> {
                try {
                    semp.acquire();
                    System.out.println("Accessing: " + NO);
                    Thread.sleep((long) (Math.random() * 6000));
                    System.out.println("-----------------" + semp.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semp.release();
                }
            });
        }

        exec.shutdown();

    }

    private static void testListenableFuture() {
        ListeningExecutorService listeningExecutorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());

        final ListenableFuture<Integer> listenableFuture = listeningExecutorService
                .submit(new Task("ListenableFuture"));
        try {
            System.out.println(listenableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //
        listenableFuture.addListener(() -> {
            try {
                System.out.println("addListener Get" + listenableFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, listeningExecutorService);

        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("FutureCallback Get" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("FutureCallback Error" + t);
            }
        });
    }

    private static void testRateLimiter() {
        ListeningExecutorService listeningExecutorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());

        RateLimiter rateLimiter = RateLimiter.create(5.0);//没秒4个提交

        for (int i = 0, len = 10; i < len; i++) {
            rateLimiter.acquire();
            listeningExecutorService.submit(new Task("Task:" + i));
        }
    }

    private static class Task implements Callable<Integer> {

        private String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("call execute.." + name);
            TimeUnit.SECONDS.sleep(1);
            return 7;
        }
    }
}
