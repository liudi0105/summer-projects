package app.webssh.app;

import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ssh_app")
public class AppEntity extends BaseJpaPO {
    private String appName;
}
