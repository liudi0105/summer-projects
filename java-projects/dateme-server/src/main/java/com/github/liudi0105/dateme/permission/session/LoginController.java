package com.github.liudi0105.dateme.permission.session;

import com.github.liudi0105.dateme.permission.user.UserAccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class LoginController {
    @Autowired
    private UserAccountService userAccountService;

}