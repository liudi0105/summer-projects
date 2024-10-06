package common.module.jpa;

import common.module.util.AppJsons;
import common.module.util.SerializableFunction;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BaseJpaRepo<E extends BaseJpaPO, D extends BaseDTO> extends DefaultJpaRepo<E> {

    Class<E> entityClass();
    Class<D> dtoClass();

    default D d(E e) {
        return AppJsons.convert(e, dtoClass());
    }

    default List<D> d(List<E> es) {
        return AppJsons.convertList(es, dtoClass());
    }

    default E e(D d) {
        return AppJsons.convert(d, entityClass());
    }

    default List<E> e(List<D> d) {
        return AppJsons.convertList(d, entityClass());
    }

    D createOrUpdate(D d);

    List<D> list(ConditionBuilder<E> conditionBuilder);

    List<D> list(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilderFunction);

    <V> List<D> listEq(SerializableFunction<E, V> conditionBuilderFunction, V v);

    Optional<D> find(ConditionBuilder<E> conditionBuilder);

    Optional<D> find(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilder);

    <V> Optional<D> findEq(SerializableFunction<E, V> func, V value);

    AppPageResult<D> pageQuery(AppPageParam pageParam, QueryBuilder<E> queryBuilder);
}
