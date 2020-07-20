package com.yuxiaobo.test.jdk8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
import sun.misc.Contended;

/**
 * @author yuxiaobo
 */
@Slf4j
public class TestObjectLayout8 {
    public static void main(String[] args) throws InterruptedException {
        log.info(VM.current().details());
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


    static void printObj(Object o) {
        log.info(ClassLayout.parseInstance(o).toPrintable());
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


    @Test
    public void testContented() {
        C[] arr = {new C(), new C()};
        printObj(new C());
        printObj(arr);
        printObj(new A());
    }
}

//@EqualsAndHashCode
class A {
    int i = 88;
    String s = "hello world";
}

class C {
    @Contended
    int x = 888;
}