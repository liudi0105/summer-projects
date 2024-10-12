package app.webssh.user;

import common.module.jpa.CrudController;
import common.module.webmvc.ApiGroup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiGroup(path = "auth-user")
public class UserController implements CrudController<UserDTO> {

    @Autowired
    @Getter
    private UserService service;

}
