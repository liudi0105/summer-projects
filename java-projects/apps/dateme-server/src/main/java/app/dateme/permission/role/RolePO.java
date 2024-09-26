package app.dateme.permission.role;

import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class RolePO extends BaseJpaPO {
}
