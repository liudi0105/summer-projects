package app.webssh.session;

import common.module.jpa.CrudController;
import common.module.webmvc.Api;
import common.module.webmvc.ApiGroup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;

@ApiGroup(path = "server")
public class ServerController implements CrudController<ServerDTO> {

    @Autowired
    @Getter
    private ServerService service;

    @Api(path = "list-all")
    public List<ServerDTO> listAll() {
        return service.getRepo().list(Function.identity());
    }

}
