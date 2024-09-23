package com.github.liudi0105.wrapper.spring.jpa;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BaseJpaRepo<E>extends AppJpaRepo<E, String>  {

}
