package com.github.liudi0105.dateme.permission.role;

import com.github.liudi0105.wrapper.spring.jpa.BaseJpaPO;
import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class RolePO extends BaseJpaPO {
}
