package com.github.liudi0105.entity;

import com.github.liudi0105.spring.jpa.BaseJpaPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariablePO extends BaseJpaPO {
    private String variableScope;
    private String variableName;
    private String variableValue;
}
