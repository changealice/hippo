package com.change.hippo.netty.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 下午1:51
 */
public class TimeServerHandlerExecutePool {


    private final ThreadPoolExecutor executor;

    public TimeServerHandlerExecutePool(int maximumPoolSize, int capacity) {
        this.executor = new ThreadPoolExecutor(maximumPoolSize, maximumPoolSize, 120L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(capacity));
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }
}
