package common.module.jpa;


import jakarta.transaction.Transactional;

public class BaseJpaService<R extends BaseJpaRepo<E, D>, E extends BaseJpaPO, D extends BaseDTO> extends AppJpaService<R, E, D> {

    @Transactional
    public D createOrUpdate(D d) {
        return getRepo().d(getRepo().save(getRepo().e(d)));
    }
}
