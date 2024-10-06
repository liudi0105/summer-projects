package app.dateme.user;

import common.module.jpa.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDTO extends BaseDTO {
    private String username;
    private String password;
    private String email;
    private String avatar;
}
