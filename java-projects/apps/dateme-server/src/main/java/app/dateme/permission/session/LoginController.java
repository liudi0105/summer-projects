package app.dateme.permission.session;

import app.dateme.user.UserAccountService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class LoginController {
    @Autowired
    private UserAccountService userAccountService;

}