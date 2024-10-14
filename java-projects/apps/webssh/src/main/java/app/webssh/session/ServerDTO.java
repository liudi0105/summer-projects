package app.webssh.session;

import common.module.jpa.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ServerDTO extends BaseDTO {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String privateKey;
    private List<String> tags;
}
