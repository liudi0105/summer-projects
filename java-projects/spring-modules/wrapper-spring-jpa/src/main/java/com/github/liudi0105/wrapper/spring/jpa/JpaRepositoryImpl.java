package com.github.liudi0105.wrapper.spring.jpa;

import com.github.liudi0105.core.util.SerializableFunction;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class JpaRepositoryImpl<E extends BaseJpaPO> extends SimpleJpaRepository<E, String> implements BaseJpaRepo<E> {
    @Autowired
    private QueryManager queryManager;

    public JpaRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public ConditionBuilder<E> cb() {
        return new ConditionBuilder<>();
    }

    public QueryBuilder<E> qb() {
        return QueryBuilder.getInstance();
    }

    protected QueryBuilder<E> preSelect(QueryBuilder<E> queryBuilder) {
        return queryBuilder;
    }

    public <V> Optional<E> findEq(SerializableFunction<E, V> func, V value) {
        return findEq(cb().eq(func, value));
    }

    public Optional<E> findEq(ConditionBuilder<E> cb) {
        return findOne(cb.toSpecification());
    }

}
