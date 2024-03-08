package com.github.liudi0105.po;

import com.github.liudi0105.spring.jpa.BaseJpaPO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPO extends BaseJpaPO {
    private String username;
    private String password;
    private String avatar;
    private String nickName;
}
