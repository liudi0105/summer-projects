package common.module.jpa;

import common.module.model.dto.BaseDTO;
import common.module.util.AppJsons;
import common.module.util.SerializableFunction;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class JpaRepositoryImpl<E extends BaseJpaPO, D extends BaseDTO> extends SimpleJpaRepository<E, String> implements BaseJpaRepo<E, D> {
    @Autowired
    private QueryManager queryManager;

    protected final Class<D> dtoClass = computeDtoClass();

    protected final Class<E> entityClass = computeEntityClass();

    public JpaRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    Class<D> computeDtoClass() {
        ParameterizedType clazz = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<D>) clazz.getActualTypeArguments()[1];
    }

    Class<E> computeEntityClass() {
        ParameterizedType clazz = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) clazz.getActualTypeArguments()[0];
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

    private List<D> list(ConditionBuilder<E> conditionBuilder) {
        List<E> all = findAll(conditionBuilder.toSpecification());
        return d(all);
    }

    public List<D> list(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilderFunction) {
        return list(conditionBuilderFunction.apply(cb()));
    }

    public <V> List<D> listEq(SerializableFunction<E, V> conditionBuilderFunction, V v) {
        return list(cb().eq(conditionBuilderFunction, v));
    }

    private Optional<D> find(ConditionBuilder<E> conditionBuilder) {
        return findOne(conditionBuilder.toSpecification()).map(this::d);
    }

    public Optional<D> find(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilder) {
        return find(conditionBuilder.apply(cb()));
    }

    public <V> Optional<D> findEq(SerializableFunction<E, V> func, V value) {
        return find(cb().eq(func, value));
    }
}
