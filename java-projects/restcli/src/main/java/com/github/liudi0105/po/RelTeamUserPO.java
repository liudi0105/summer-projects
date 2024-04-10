package com.github.liudi0105.po;

import com.github.liudi0105.spring.jpa.BaseJpaPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelTeamUserPO extends BaseJpaPO {
    private String username;
    private String teamName;
}
