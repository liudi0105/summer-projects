package common.module.jpa;

import common.module.util.AppJsons;
import common.module.util.SerializableFunction;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class JpaRepositoryImpl<E extends BaseJpaPO, D extends BaseDTO> extends SimpleJpaRepository<E, String> implements BaseJpaRepo<E, D> {
    @Autowired
    private QueryManager queryManager;

    protected Class<D> dtoClass;

    protected Class<E> entityClass;

    public JpaRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        entityClass = entityInformation.getJavaType();
    }

    public D d(E e) {
        return AppJsons.convert(e, dtoClass);
    }

    public List<D> d(List<E> es) {
        return AppJsons.convertList(es, dtoClass);
    }

    public E e(D d) {
        return AppJsons.convert(d, entityClass);
    }

    public List<E> e(List<D> d) {
        return AppJsons.convertList(d, entityClass);
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

    @Override
    public D createOrUpdate(D d) {
        E save = save(e(d));
        return d(save);
    }

    @Override
    public List<D> list(ConditionBuilder<E> conditionBuilder) {
        List<E> all = findAll(conditionBuilder.toSpecification());
        return d(all);
    }

    @Override
    public List<D> list(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilderFunction) {
        return list(conditionBuilderFunction.apply(cb()));
    }

    @Override
    public <V> List<D> listEq(SerializableFunction<E, V> conditionBuilderFunction, V v) {
        return list(cb().eq(conditionBuilderFunction, v));
    }

    @Override
    public Optional<D> find(ConditionBuilder<E> conditionBuilder) {
        return findOne(conditionBuilder.toSpecification()).map(this::d);
    }

    @Override
    public Optional<D> find(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilder) {
        return find(conditionBuilder.apply(cb()));
    }

    @Override
    public <V> Optional<D> findEq(SerializableFunction<E, V> func, V value) {
        return find(cb().eq(func, value));
    }

    @Override
    public AppPageResult<D> pageQuery(AppPageParam pageParam, QueryBuilder<E> queryBuilder) {
        Page<E> all = findAll(queryBuilder.toSpecification(), AppPages.checkPageParam(pageParam));
        return AppPageResult.of(all.getTotalElements(), all.getContent()).map(this::d);
    }
}
