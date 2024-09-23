package com.github.liudi0105.wrapper.spring.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppPageResult<T> {
    private Long totalElements;
    private List<T> content;

    public static <T> AppPageResult<T> of(Long totalElements, List<T> content) {
        return new AppPageResult<>(totalElements, content);
    }
}
