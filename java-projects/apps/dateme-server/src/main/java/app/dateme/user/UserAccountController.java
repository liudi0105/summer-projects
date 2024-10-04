package app.dateme.user;

import common.module.webmvc.ApiGroup;
import common.module.jpa.BaseController;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiGroup(path = "auth/user-account")
public class UserAccountController implements BaseController<UserAccountService, UserAccountDTO> {

    @Autowired
    @Getter
    private UserAccountService service;

}
