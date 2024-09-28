package app.dateme.permission.role;

import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "auth_role")
public class RolePO extends BaseJpaPO {
    private String roleCode;
    private String description;
}
