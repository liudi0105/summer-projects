package common.module.jpa;

import common.module.model.dto.BaseDTO;

public interface BaseJpaRepo<E extends BaseJpaPO, D extends BaseDTO> extends DefaultJpaRepo<E> {
}
