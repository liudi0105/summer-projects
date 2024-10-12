package app.webssh.session;

import common.module.jpa.CrudController;
import common.module.webmvc.ApiGroup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiGroup(path = "server")
public class ServerController implements CrudController<ServerDTO> {

    @Autowired
    @Getter
    private ServerService service;

}
