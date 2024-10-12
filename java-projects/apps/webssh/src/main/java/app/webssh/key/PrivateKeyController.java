package app.webssh.key;

import common.module.jpa.CrudController;
import common.module.webmvc.ApiGroup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiGroup(path = "private-key")
public class PrivateKeyController implements CrudController<PrivateKeyDTO> {

    @Autowired
    @Getter
    private PrivateKeyService service;

}
