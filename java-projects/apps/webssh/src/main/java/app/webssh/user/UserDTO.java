package app.webssh.user;

import common.module.jpa.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserDTO extends BaseDTO {
    private String username;
    private String password;
}
