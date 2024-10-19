package app.webssh.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class SshConnectMessage {
    //操作
    private String operate;
    private String host;
    //端口号默认为22
    private Integer port = 22;
    private String username;
    private String password;
    private String command = "";

}
