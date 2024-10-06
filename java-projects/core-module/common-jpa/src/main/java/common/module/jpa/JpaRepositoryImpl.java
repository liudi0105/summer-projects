package common.module.jpa;

import common.module.util.SerializableFunction;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional(readOnly = true)
public class JpaRepositoryImpl<E extends BaseJpaPO, D extends BaseDTO> extends SimpleJpaRepository<E, String> implements BaseJpaRepo<E, D> {
    private QueryManager queryManager;

    @Getter
    @Accessors(fluent = true)
    protected Class<D> dtoClass;

    @Getter
    @Accessors(fluent = true)
    protected Class<E> entityClass;

    public JpaRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager, Class<?> repositoryInterface) {
        super(entityInformation, entityManager);
        entityClass = entityInformation.getJavaType();
        ParameterizedType clazz = (ParameterizedType) repositoryInterface.getGenericInterfaces()[0];
        dtoClass = (Class<D>) clazz.getActualTypeArguments()[1];
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
    @Transactional
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
