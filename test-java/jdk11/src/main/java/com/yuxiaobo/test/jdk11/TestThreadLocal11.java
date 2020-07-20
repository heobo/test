package com.yuxiaobo.test.jdk11;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

import static com.yuxiaobo.test.jdk11.TestObjectLayout11.printObj;
import static com.yuxiaobo.test.jdk11.TestObjectLayout11.printVm;

/**
 * @author yuxiaobo
 */
public class TestThreadLocal11 {

    static {
        printVm();
    }

    static class A {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();

        public A(A prev) {
        }

        A prev;
    }

    public static void main(String[] args) {
        int size = 1000000;
        A a = null;

        for (int i = 0; i < size; i++) {
            (a = new A(a)).threadLocal.set("Hello: " + i);
        }


        Object threadLocalMap = getThreadLocalMap();
        printObj(threadLocalMap);
        printObj(getTable(threadLocalMap));
        System.gc();
        printObj(threadLocalMap);
        printObj(getTable(threadLocalMap));

        printObj(a);

        A b = a;
        int i = 0;
        while (b != null) {
            b = b.prev;
            i++;
        }
        System.out.println("i: " + i);
    }

    public static Object getThreadLocalMap() {
        try {
            Field field = Thread.class.getDeclaredField("threadLocals");
            field.setAccessible(true);
            return field.get(Thread.currentThread());
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    @SneakyThrows
    public static Object[] getTable(Object map) {
        Field field = map.getClass().getDeclaredField("table");
        field.setAccessible(true);
        return (Object[]) field.get(map);
    }
}
