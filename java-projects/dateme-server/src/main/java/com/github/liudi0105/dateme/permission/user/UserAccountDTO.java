package com.github.liudi0105.dateme.permission.user;

import com.github.liudi0105.wrapper.spring.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserAccountDTO extends BaseDTO {
    private String username;
    private String password;
    private String avatar;
}
