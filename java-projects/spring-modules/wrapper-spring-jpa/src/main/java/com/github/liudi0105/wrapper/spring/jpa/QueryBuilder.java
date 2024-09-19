package com.github.liudi0105.wrapper.spring.jpa;

import com.github.liudi0105.spring.util.AppReflections;
import jakarta.persistence.criteria.*;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

@Accessors(chain = true)
public class QueryBuilder<E> {
    private ConditionBuilder<E> conditionBuilder = new ConditionBuilder<E>();

    private ConditionBuilder<E> where;
    private ConditionBuilder<E> having;
    private Boolean distinct;
    private String[] orderBy;
    private String[] select;
    private Integer offset;
    private Boolean asc = false;


    public Specification<E> toSpecification() {
        return this::toPredicate;
    }

    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        if (distinct != null) {
            criteriaQuery.distinct(distinct);
        }

        if (orderBy != null) {
            Order[] orders = Arrays.stream(orderBy)
                    .map(v -> asc == null || asc ? cb.asc(root.get(v)) : cb.desc(root.get(v)))
                    .toArray(Order[]::new);
            criteriaQuery.orderBy(orders);
        }

        if (where != null) {
            Predicate predicate = where.toPredicate(root, cb);
            criteriaQuery.where(predicate);
        }

        if (select != null) {
            Selection<String>[] selections = Arrays.stream(select).map(root::get).toArray(Selection[]::new);
            criteriaQuery.multiselect(selections);
        }
        return criteriaQuery.getRestriction();
    }

    public static <T> QueryBuilder<T> on(UnaryOperator<ConditionBuilder<T>> condition) {
        QueryBuilder<T> of = QueryBuilder.getInstance();
        of.where = condition.apply(new ConditionBuilder<T>());
        return of;
    }

    public static <T> QueryBuilder<T> getInstance() {
        return new QueryBuilder<>();
    }

    public static <T> QueryBuilder<T> fromCondition(UnaryOperator<ConditionBuilder<T>> condition) {
        return QueryBuilder.<T>getInstance().appendCondition(condition);
    }

    public QueryBuilder<E> appendCondition(UnaryOperator<ConditionBuilder<E>> condition) {
        this.where = condition.apply(conditionBuilder);
        return this;
    }

    public QueryBuilder<E> withCondition(ConditionBuilder<E> c) {
        this.where = c;
        return this;
    }

    public static <E> ConditionBuilder<E> or(ConditionBuilder<E>... conditionBuilders) {
        List<ConditionBuilder<E>> collect = Arrays.stream(conditionBuilders)
                .filter(v -> !v.getType().equals(ConditionBuilder.Type.EMPTY_CONDITION))
                .toList();

        if (collect.isEmpty()) {
            return new ConditionBuilder<>();
        } else if (collect.size() == 1) {
            return collect.get(0);
        }
        return new ConditionBuilder<E>()
                .setType(ConditionBuilder.Type.OR)
                .setOrCondition(collect.toArray(new ConditionBuilder[0]));
    }

    public static <E> ConditionBuilder<E> and(ConditionBuilder<E>... conditionBuilders) {
        List<ConditionBuilder<E>> list = Arrays.stream(conditionBuilders)
                .filter(v -> !v.getType().equals(ConditionBuilder.Type.EMPTY_CONDITION))
                .toList();

        if (list.isEmpty()) {
            return new ConditionBuilder<>();
        } else if (list.size() == 1) {
            return list.get(0);
        }
        return new ConditionBuilder<E>()
                .setType(ConditionBuilder.Type.AND)
                .setAndCondition(list.toArray(ConditionBuilder[]::new));
    }

    public QueryBuilder<E> distinct() {
        distinct = true;
        return this;
    }

    public final QueryBuilder<E> orderBy(SerializableFunction<E, ? extends Comparable<?>>... functions) {
        orderBy = Arrays.stream(functions).map(AppReflections::getFieldName).toArray(String[]::new);
        return this;
    }

    public QueryBuilder<E> asc() {
        this.asc = true;
        return this;
    }

    public QueryBuilder<E> desc() {
        this.asc = false;
        return this;
    }

    public QueryBuilder<E> having(UnaryOperator<ConditionBuilder<E>> condition) {
        having = condition.apply(conditionBuilder);
        return this;
    }

    public QueryBuilder<E> offset(int offset) {
        this.offset = offset;
        return this;
    }
}
