//package com.yuxiaobo.test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.openjdk.jol.info.ClassLayout;
//import org.openjdk.jol.vm.VM;
//
///**
// * @author yuxiaobo
// */
//@Slf4j
//public class TestObjectLayout {
//    public static void main(String[] args) throws InterruptedException {
//        log.info(VM.current().details());
//        printObj("hello world");
//        A a = new A();
//        printObj(a);
////        log.info("hash: " + toBytesString(a.hashCode()));
////        log.info("hash: " + Integer.toBinaryString(a.hashCode()));
//        printObj(a);
//
//        synchronized (a) {
//            printObj(a);
//        }
//
//        Thread.sleep(10000L);
//        printObj(a);
//        synchronized (a) {
//            printObj(a);
//        }
//    }
//
//
//    static void printObj(Object o) {
//        log.info(ClassLayout.parseInstance(o).toPrintable());
//    }
//
//    static String toBytesString(int input) {
//        int tmp = input;
//        StringBuilder builder = new StringBuilder();
//        do {
//            builder.append(Integer.toBinaryString((tmp & 0xFF) | 0x100).substring(1));
//            builder.append(" ");
//            tmp = tmp >> 8;
//        } while (tmp > 0);
//        return builder.toString();
//    }
//
//
//}
//
////@EqualsAndHashCode
//class A {
//    int i = 88;
//    String s = "hello world";
//}
//
