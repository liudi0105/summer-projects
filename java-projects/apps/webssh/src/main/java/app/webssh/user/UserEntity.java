package app.webssh.user;

import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "ssh_user")
public class UserEntity extends BaseJpaPO {
    private String username;
    private String password;
}
