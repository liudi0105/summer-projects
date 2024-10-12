package app.webssh.session;

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
@Table(name = "ssh_server")
public class ServerEntity extends BaseJpaPO {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String privateKey;
}
