package com.github.liudi0105.po;

import com.github.liudi0105.spring.jpa.BaseJpaPO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AuthenticationPO extends BaseJpaPO {
    private String authenticationType;
    private String headerKey;
    private String headerValue;
}
