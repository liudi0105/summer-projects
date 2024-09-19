package com.github.liudi0105.wrapper.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BaseJpaRepo<E> extends JpaRepository<E, String>, JpaSpecificationExecutor<E> {

}
