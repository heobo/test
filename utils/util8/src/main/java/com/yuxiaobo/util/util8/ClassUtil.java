package com.yuxiaobo.util.util8;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuxiaobo
 */
public class ClassUtil {

    private ClassUtil() {
    }

    @NotNull
    public static String classLoadersToString(@NotNull Collection<ClassLoader> classLoaders) {
        return classLoaders.stream()
                .map(c -> c == null ? "null" : c.getClass().getName())
                .collect(Collectors.joining(" -> "));
    }

    @NotNull
    public static String getParentLoaderChain(@Nullable ClassLoader classLoader) {
        List<ClassLoader> list = new ArrayList<>();
        list.add(classLoader);
        while (classLoader != null) {
            classLoader = classLoader.getParent();
            list.add(classLoader);
        }
        return classLoadersToString(list);
    }
}
