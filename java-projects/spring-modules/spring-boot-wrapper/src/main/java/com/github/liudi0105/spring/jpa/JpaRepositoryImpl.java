package com.github.liudi0105.spring.jpa;

import com.github.liudi0105.summer.core.ioc.annotation.Autowired;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class JpaRepositoryImpl<E extends BaseJpaPO> extends SimpleJpaRepository<E, String> implements BaseJpaRepo<E> {
    @Autowired
    private QueryManager queryManager;

    public JpaRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

}
