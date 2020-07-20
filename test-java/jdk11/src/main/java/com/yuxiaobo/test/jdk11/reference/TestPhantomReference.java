package com.yuxiaobo.test.jdk11.reference;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuxiaobo
 */
@Slf4j
public class TestPhantomReference {

    static int i = 0;

    @Data
    static class A {
        int number = i++;
    }

    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<A> queue = new ReferenceQueue<>();
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread thread = new Thread(() -> {
            Reference<? extends A> a;
            while (true) {
                a = queue.poll();
                if (a != null) {
                    log.info("GET -> " + a + " " + atomicInteger.incrementAndGet());
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();


        int size = 100000;
        Set<PhantomReference<?>> set = new HashSet<>();
        for (int x = 0; x < size; x++) {
            set.add(new PhantomReference<>(new A(), queue));
//            Thread.sleep(10L);
            if ((x & 0xFF) == 0) {
                System.gc();
            }
        }


    }
}
