package com.github.liudi0105.wrapper.spring.jpa;

import com.github.liudi0105.core.util.SerializableFunction;
import com.github.liudi0105.spring.util.AppReflections;
import com.github.liudi0105.spring.util.AppStrings;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

@Getter
@Setter
@Accessors(chain = true)
public class ConditionBuilder<E> {
    private String field;
    private Tuple2<String, ?> t2;
    private Tuple3<String, ?, ?> t3;
    public ConditionBuilder<E>[] orCondition;
    public ConditionBuilder<E>[] andCondition;

    private Type type;

    public ConditionBuilder() {
        this.type = Type.EMPTY_CONDITION;
    }

    @Getter
    @AllArgsConstructor
    public static class Tuple2<T1, T2> {
        private T1 t1;
        private T2 t2;
    }

    @Getter
    @AllArgsConstructor
    public static class Tuple3<T1, T2, T3> {
        private T1 t1;
        private T2 t2;
        private T3 t3;
    }

    public QueryBuilder<E> toQB() {
        return QueryBuilder.<E>getInstance().appendCondition(this::apply);
    }

    public Specification<E> toSpecification() {
        return this::toPredicate;
    }

    public Predicate toPredicate(Root<E> root, CriteriaBuilder cb) {
        return switch (getType()) {
            case NOT_EQUAL -> cb.notEqual(root.get(getT2().getT1()), getT2().getT2());
            case EQUAL -> cb.equal(root.get(getT2().getT1()), getT2().getT2());
            case LIKE -> cb.like(root.get(getT2().getT1()), (String) getT2().getT2());
            case OR -> cb.or(Arrays.stream(orCondition).map(v -> v.toPredicate(root, cb)).toArray(Predicate[]::new));
            case AND -> cb.and(Arrays.stream(andCondition).map(v -> v.toPredicate(root, cb)).toArray(Predicate[]::new));
            case IS_NULL -> cb.isNull(root.get(getField()));
            case NOT_NULL -> cb.isNotNull(root.get(getField()));
            case IS_EMPTY -> cb.isEmpty(root.get(getField()));
            case NOT_EMPTY -> cb.isNotEmpty(root.get(getField()));
            case BETWEEN -> cb.between(root.get(t3.t1), (Comparable) t3.t2, (Comparable) t3.t3);
            case GREAT_THAN, AFTER -> cb.greaterThan(root.get(t2.t1), (Comparable) t2.t2);
            case GREAT_EQUAL -> cb.greaterThanOrEqualTo(root.get(t2.t1), (Comparable) t2.t2);
            case LESS_THAN, BEFORE -> cb.lessThan(root.get(t2.t1), (Comparable) t2.t2);
            case LESS_EQUAL -> cb.lessThanOrEqualTo(root.get(t2.t1), (Comparable) t2.t2);
            case IN -> cb.in(root.get(t2.t1)).value(t2.t2);
            case EMPTY_CONDITION -> cb.and();
            case LIKE_IGNORE_CASE -> cb.like(cb.lower(root.get(t2.t1)), (String) t2.t2);
            case NOT_CONTAIN -> cb.notLike(root.get(t2.t1), (String) t2.t2);
        };
    }

    public ConditionBuilder<E> combine(ConditionBuilder<E> cb) {
        Tuple2<String, ?> t2 = cb.t2;

        if (t2 != null && AppStrings.isBlank(t2.t2)) {
            return this;
        }
        Tuple3<String, ?, ?> t3 = cb.t3;
        if (t3 != null && AppStrings.anyBlank(t3.t2, t3.t3)) {
            return this;
        }
        if (Type.EMPTY_CONDITION.equals(cb.type)) {
            return this;
        }
        if (Type.EMPTY_CONDITION.equals(type)) {
            return cb;
        }

        return QueryBuilder.and(this, cb);
    }

    private ConditionBuilder<E> ofField(Type type, SerializableFunction<E, ?> field) {
        return ofField(type, AppReflections.getFieldName(field));
    }

    public ConditionBuilder<E> ofField(Type type, String field) {
        return new ConditionBuilder<E>().setType(type).setField(field);
    }

    private <V> ConditionBuilder<E> ofT3(Type type, SerializableFunction<E, V> function, V v1, V v2) {
        return ofT3(type, new Tuple3<>(AppReflections.getFieldName(function), v1, v2));
    }

    public ConditionBuilder<E> ofT3(Type type, Tuple3<String, ?, ?> t3) {
        return new ConditionBuilder<E>().setType(type).setT3(t3);
    }

    private ConditionBuilder<E> ofT2(Type type, SerializableFunction<E, ?> function, Object value) {
        return ofT2(type, new Tuple2<>(AppReflections.getFieldName(function), value));
    }

    private ConditionBuilder<E> ofT2(Type type, String field, Object value) {
        return ofT2(type, new Tuple2<>(field, value));
    }

    private ConditionBuilder<E> ofT2(Type type, Tuple2<String, ?> t2) {
        return new ConditionBuilder<E>().setType(type).setT2(t2);
    }

    public <V> ConditionBuilder<E> in(SerializableFunction<E, V> function, Collection<V> value) {
        if (CollectionUtils.isEmpty(value)) {
            return this;
        }
        return combine(ofT2(Type.IN, function, value));
    }

    public <V> ConditionBuilder<E> eq(SerializableFunction<E, V> function, V value) {
        return combineT2(Type.EQUAL, function, value);
    }

    public <V> ConditionBuilder<E> notEq(SerializableFunction<E, V> function, V value) {
        return combineT2(Type.NOT_EQUAL, function, value);
    }

    @SafeVarargs
    public final ConditionBuilder<E> or(ConditionBuilder<E>... c) {
        return QueryBuilder.or(this, QueryBuilder.or(c));
    }

    @SafeVarargs
    public final ConditionBuilder<E> or(Function<ConditionBuilder<E>, ConditionBuilder<E>>... func) {
        ConditionBuilder[] conditionBuilders = new ConditionBuilder[func.length];
        for (int i = 0; i < conditionBuilders.length; i++) {
            conditionBuilders[i] = func[i].apply(new ConditionBuilder<>());
        }
        return or(conditionBuilders);
    }

    public ConditionBuilder<E> startsWith(SerializableFunction<E, String> function, String value) {
        return combineT2(Type.LIKE, function, "%" + value);
    }

    public ConditionBuilder<E> endsWith(SerializableFunction<E, String> function, String value) {
        return combineT2(Type.LIKE, function, "%" + value + "%");
    }

    public ConditionBuilder<E> notContain(SerializableFunction<E, String> function, String value) {
        return combineT2(Type.NOT_CONTAIN, function, value);
    }

    public ConditionBuilder<E> isNull(SerializableFunction<E, ?> function) {
        return combine(ofField(Type.IS_NULL, function));
    }

    public ConditionBuilder<E> isNotNull(SerializableFunction<E, ?> function) {
        return combine(ofField(Type.NOT_NULL, function));
    }

    public ConditionBuilder<E> isEmpty(SerializableFunction<E, String> function) {
        return combine(ofField(Type.IS_EMPTY, function));
    }

    public ConditionBuilder<E> containsIgnoreCase(SerializableFunction<E, String> function, String value) {
        return combineT2(Type.LIKE_IGNORE_CASE, function, "%" + value.trim().toLowerCase() + "%");
    }

    public <V extends Comparable<V>> ConditionBuilder<E> before(SerializableFunction<E, V> function, V value) {
        return lt(function, value);
    }

    public <V extends Comparable<V>> ConditionBuilder<E> lt(SerializableFunction<E, V> function, V value) {
        return combineT2(Type.LESS_THAN, function, value);
    }

    public <V extends Comparable<V>> ConditionBuilder<E> lte(SerializableFunction<E, V> function, V value) {
        return combineT2(Type.LESS_EQUAL, function, value);
    }

    public <V extends Comparable<V>> ConditionBuilder<E> gt(SerializableFunction<E, V> function, V value) {
        return combineT2(Type.GREAT_THAN, function, value);
    }

    public <V extends Comparable<V>> ConditionBuilder<E> between(SerializableFunction<E, V> function, V start, V end) {
        if (ObjectUtils.anyNull(start, end)) {
            return this;
        }
        return combine(ofT3(Type.BETWEEN, function, start, end));
    }

    private Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return toPredicate(root, criteriaBuilder);
    }

    private ConditionBuilder<E> apply(ConditionBuilder<E> v) {
        return this;
    }

    public <V> ConditionBuilder<E> combineT2(Type type, SerializableFunction<E, V> function, V value) {
        if (value == null) {
            return this;
        }
        return combine(ofT2(type, function, value));
    }

    public enum Type {
        EMPTY_CONDITION,
        EQUAL, NOT_EQUAL, LIKE, IN,
        IS_NULL, NOT_NULL, IS_EMPTY, NOT_EMPTY,
        OR, AND,
        BEFORE, AFTER, NOT_CONTAIN,
        GREAT_THAN, LESS_THAN, LESS_EQUAL, GREAT_EQUAL, BETWEEN,
        LIKE_IGNORE_CASE
    }
}
