package app.dateme.user;

import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_user_detail")
public class UserDetailPO extends BaseJpaPO {
}
