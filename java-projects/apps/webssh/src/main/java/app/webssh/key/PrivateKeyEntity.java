package app.webssh.key;

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
@Table(name = "ssh_private_Key")
public class PrivateKeyEntity extends BaseJpaPO {
    private String keyId;
}
