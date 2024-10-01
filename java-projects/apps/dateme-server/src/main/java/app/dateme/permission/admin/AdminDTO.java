package app.dateme.permission.admin;

import common.module.jpa.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AdminDTO extends BaseDTO {
    private String email;
    private String phone;
    private String name;
    private String password;
}
