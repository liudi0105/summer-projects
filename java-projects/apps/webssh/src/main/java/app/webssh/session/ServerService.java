package app.webssh.session;

import common.module.jpa.BaseJpaService;
import org.springframework.stereotype.Component;

@Component
public class ServerService extends BaseJpaService<ServerRepo, ServerEntity, ServerDTO> {
}
