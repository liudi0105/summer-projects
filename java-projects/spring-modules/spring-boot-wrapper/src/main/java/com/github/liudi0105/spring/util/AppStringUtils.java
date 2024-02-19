package com.github.liudi0105.spring.util;

import com.google.common.base.CaseFormat;

public class AppStringUtils {

    public static String underline(String str) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    public static String hump(String str) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

}
