package app.webssh.key;

import common.module.jpa.BaseJpaService;
import org.springframework.stereotype.Component;

@Component
public class PrivateKeyService extends BaseJpaService<PrivateKeyRepo, PrivateKeyEntity, PrivateKeyDTO> {
    private String keyId;
}
