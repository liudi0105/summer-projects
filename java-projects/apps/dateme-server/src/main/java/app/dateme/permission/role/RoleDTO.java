package app.dateme.permission.role;

import common.module.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RoleDTO extends BaseDTO {
    private String roleCode;
    private String description;
}
