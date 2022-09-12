package com.demo.lyr.hashmap;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class HashMapDeadLoop {
    public static void main(String[] args)  {

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        ReentrantLock lock = new ReentrantLock();

        try {
            lock.lock();
            lock.tryLock(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean b = lock.tryLock();

        final HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(
                    () -> map.put(UUID.randomUUID().toString(), "")
            ).start();
        }
    }
}
