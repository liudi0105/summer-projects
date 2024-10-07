package app.dateme.user;

import common.module.webmvc.ApiGroup;
import common.module.jpa.CrudController;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiGroup(path = "auth/user-account")
public class UserAccountController implements CrudController<UserAccountDTO> {

    @Autowired
    @Getter
    private UserAccountService service;

}
