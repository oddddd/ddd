package com.example.ddd.library;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPoolManager
 *
 * @author wjp
 * @desc
 * @date Created in 下午5:30 2018/5/4
 */
public class ThreadPoolManager {

    private ExecutorService service;

    private ThreadPoolManager() {
        service = Executors.newCachedThreadPool();
    }

    private static volatile ThreadPoolManager manager;

    public static ThreadPoolManager getInstance() {
        if (null == manager) {
            synchronized (ThreadPoolManager.class) {
                if (null == manager) {
                    manager = new ThreadPoolManager();
                }
            }
        }
        return manager;
    }

    public void addTask(Runnable runnable) {
        service.submit(runnable);
    }
}
