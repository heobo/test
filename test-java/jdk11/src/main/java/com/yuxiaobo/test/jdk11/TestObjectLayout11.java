package com.yuxiaobo.test.jdk11;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuxiaobo
 */
@Slf4j
public class TestObjectLayout11 {
    public static void main(String[] args) throws InterruptedException {
        printVm();
        printObj("hello world");
        printObj("你好");
        printObj("\uD83E\uDD90 is shrimp");
        A a = new A();
        printObj(a);
//        log.info("hash: " + toBytesString(a.hashCode()));
//        log.info("hash: " + Integer.toBinaryString(a.hashCode()));
        printObj(a);

        synchronized (a) {
            printObj(a);
        }

        Thread.sleep(10000L);
        printObj(a);
        synchronized (a) {
            printObj(a);
        }
    }


    public static void printObj(Object o) {
        log.info(ClassLayout.parseInstance(o).toPrintable());
    }

    public static void printVm() {
        log.info(VM.current().details());

    }

    static String toBytesString(int input) {
        int tmp = input;
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(Integer.toBinaryString((tmp & 0xFF) | 0x100).substring(1));
            builder.append(" ");
            tmp = tmp >> 8;
        } while (tmp > 0);
        return builder.toString();
    }

    @Before
    public void before() {
        log.info(VM.current().details());
    }

    @Test
    public void testMapLayout() {
        printObj(new HashMap<>(16, .8F));
        printObj(new ConcurrentHashMap<>(16, .9F));
        printObj(new TreeMap<>());
    }


}

//@EqualsAndHashCode
class A {
    int i = 88;
    String s = "hello world";
}
