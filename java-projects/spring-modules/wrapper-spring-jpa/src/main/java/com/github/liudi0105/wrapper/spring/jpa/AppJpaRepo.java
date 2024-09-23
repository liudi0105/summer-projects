package com.github.liudi0105.wrapper.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppJpaRepo <E, I> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {
}
