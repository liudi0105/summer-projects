package common.module.jpa;


import common.module.model.dto.BaseDTO;

public class BaseJpaService<R extends BaseJpaRepo<E>, E extends BaseJpaPO, D extends BaseDTO> extends AppJpaService<R, E, String, D> {
}
