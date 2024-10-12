package app.webssh.key;

import common.module.jpa.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PrivateKeyDTO extends BaseDTO {
    private String keyId;
}
