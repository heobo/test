package com.yuxiaobo.test.jdk11;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author yuxiaobo
 */
@Data
@Slf4j
public class TestVarHandle {

    int x;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        VarHandle handle = MethodHandles.lookup().findVarHandle(TestVarHandle.class, "x", int.class);

        TestVarHandle data = new TestVarHandle();
        Integer eight = Integer.valueOf(888);
        Integer anotherEight = 888;
        data.setX(eight);
        log.info(data.toString());
        Object o = handle.get(data);
        log.info("o is {}, {}", o.getClass().getName(), o);
        Object o1 = handle.compareAndSet(new Object(), anotherEight, 555);
        log.info("o1 is {}, {}", o1.getClass().getName(), o1);
        o = handle.get(data);
        log.info("o is {}, {}", o.getClass().getName(), o);
        log.info(data.toString());
    }

}
