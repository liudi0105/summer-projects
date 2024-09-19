package com.github.liudi0105.wrapper.spring.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppPageResult<T> {
    public static <T> AppPageResult<T> of(long count, List<T> content) {
        return new AppPageResult<>();
    }
}
