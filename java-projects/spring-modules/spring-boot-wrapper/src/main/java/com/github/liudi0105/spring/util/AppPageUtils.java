package com.github.liudi0105.spring.util;

import com.github.liudi0105.spring.error.AppError;

public class AppPageUtils {

    public static void checkPageParam(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex < 1) {
            throw new AppError("pageIndex must great than 0");
        }

        if (pageSize == null || pageSize < 1) {
            throw new AppError("pageSize must great than 0");
        }
    }
}
