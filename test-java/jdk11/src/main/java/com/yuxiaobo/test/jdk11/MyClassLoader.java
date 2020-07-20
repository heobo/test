package com.yuxiaobo.test.jdk11;

/**
 * @author yuxiaobo
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
        super("my", ClassLoader.getPlatformClassLoader());
    }
}
