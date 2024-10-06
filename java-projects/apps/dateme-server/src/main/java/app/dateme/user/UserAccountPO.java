package app.dateme.user;


import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_user_account")
public class UserAccountPO extends BaseJpaPO {
    private String username;
    private String email;
    private String password;
}
