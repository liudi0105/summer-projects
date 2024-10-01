package common.module.jpa;

import common.module.util.SerializableFunction;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BaseJpaRepo<E extends BaseJpaPO, D extends BaseDTO> extends DefaultJpaRepo<E> {
    List<D> list(ConditionBuilder<E> conditionBuilder);

    List<D> list(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilderFunction);

    <V> List<D> listEq(SerializableFunction<E, V> conditionBuilderFunction, V v);

    Optional<D> find(ConditionBuilder<E> conditionBuilder);

    Optional<D> find(Function<ConditionBuilder<E>, ConditionBuilder<E>> conditionBuilder);

    <V> Optional<D> findEq(SerializableFunction<E, V> func, V value);

    AppPageResult<D> pageQuery(AppPageParam pageParam, QueryBuilder<E> queryBuilder);
}
