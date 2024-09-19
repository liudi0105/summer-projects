package com.github.liudi0105.core.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class AppStrings {

    public static String underline(String str) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    public static String hump(String str) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    public static boolean isBlank(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String s) {
            return StringUtils.isBlank(s);
        }
        return false;
    }

    public static boolean anyBlank(Object... o) {
        if (o == null) {
            return true;
        }
        return Arrays.stream(o).anyMatch(AppStrings::isBlank);
    }
}
