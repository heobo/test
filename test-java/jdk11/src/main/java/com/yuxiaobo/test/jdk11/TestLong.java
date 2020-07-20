package com.yuxiaobo.test.jdk11;

/**
 * @author yuxiaobo
 */
public class TestLong {
    long l;

    public TestLong(long input) {
        this.l = input;
    }

    @Override
    protected void finalize() throws Throwable {
        System.gc();
    }
}
