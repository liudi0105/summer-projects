package common.module.jpa;

import common.module.model.dto.BaseDTO;

import java.util.List;

public interface BaseJpaRepo<E extends BaseJpaPO, D extends BaseDTO> extends DefaultJpaRepo<E> {
    List<E> findAllDtoById(Iterable<String> strings);
}
