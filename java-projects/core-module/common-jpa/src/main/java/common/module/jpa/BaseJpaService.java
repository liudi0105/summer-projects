package common.module.jpa;


public class BaseJpaService<R extends BaseJpaRepo<E, D>, E extends BaseJpaPO, D extends BaseDTO> extends AppJpaService<R, E, String, D> {
}
