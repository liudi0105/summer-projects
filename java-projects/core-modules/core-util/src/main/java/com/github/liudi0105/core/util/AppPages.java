package com.github.liudi0105.core.util;

public class AppPages {

    public static void checkPageParam(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex < 1) {
            throw new RuntimeException("pageIndex must great than 0");
        }

        if (pageSize == null || pageSize < 1) {
            throw new RuntimeException("pageSize must great than 0");
        }
    }
}
