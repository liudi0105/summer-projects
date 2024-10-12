package app.webssh.user;

import common.module.jpa.BaseJpaService;
import org.springframework.stereotype.Component;

@Component
public class UserService extends BaseJpaService<UserRepo, UserEntity, UserDTO> {
}
