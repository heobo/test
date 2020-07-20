package com.yuxiaobo.test.jdk11.collection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yuxiaobo
 */
@Slf4j
public class TestBlockingQueue {

    @SneakyThrows
    @Test
    public void test1() {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
        final Thread main = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(10000L);
            } catch (Exception e) {
                log.error("", e);
            }
            main.interrupt();
        }).start();

        for (int i = 0; i < 20; i++) {
            String s = "hello " + i;
            switch (i & 3) {
                case 0:
                    log.info("add : " + queue.add(s));
                    break;
                case 1:
                    queue.put(s);
                    log.info("put: " + i);
                    break;
                case 2:
                    log.info("offer: " + queue.offer(s));
                    break;
                case 3:
                    log.info("offer timeout: " + queue.offer(s, 5, TimeUnit.SECONDS));
                default:
            }
            ;
        }

        System.out.println("OK");
    }
}
