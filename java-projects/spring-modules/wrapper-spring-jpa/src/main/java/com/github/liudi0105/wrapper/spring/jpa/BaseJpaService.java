package com.github.liudi0105.wrapper.spring.jpa;

import com.github.liudi0105.wrapper.spring.common.dto.BaseDTO;

public class BaseJpaService<R extends BaseJpaRepo<E>, E extends BaseJpaPO, D extends BaseDTO> extends AppJpaService<R, E, String, D> {
}
