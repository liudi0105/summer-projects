package app.dateme.permission.user;

import common.module.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserAccountDTO extends BaseDTO {
    private String username;
    private String password;
    private String avatar;
}
