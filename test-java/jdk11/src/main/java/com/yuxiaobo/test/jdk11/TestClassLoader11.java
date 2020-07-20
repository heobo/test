package com.yuxiaobo.test.jdk11;

import com.yuxiaobo.util.util8.ClassUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yuxiaobo
 */
@Slf4j
public class TestClassLoader11 {

    @SneakyThrows
    public static void main(String[] args) {
        log.info("hello");
        log.info(ClassUtil.getParentLoaderChain(TestClassLoader11.class.getClassLoader()));

        log.info(P.X);
        log.info(P.y);

        System.gc();

        log.info(P.X);
        log.info(P.y);

    }

    static class P {
        static final String X = "hello888";
        static String y = "world";

        static {
            System.out.println("initialing P");
        }
    }
}


