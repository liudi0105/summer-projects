package com.github.liudi0105.wrapper.spring.jpa;

import com.github.liudi0105.core.util.AppJsons;
import com.github.liudi0105.core.util.AppPages;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;

import java.util.List;
import java.util.Map;

public class QueryManager {
    private final EntityManager entityManager;

    public QueryManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Query createQuery(SqlBuilder oldSqlBuilder) {
        Query nativeQuery = entityManager.createNativeQuery(oldSqlBuilder.toString());
        oldSqlBuilder.getParams().forEach(nativeQuery::setParameter);
        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return nativeQuery;
    }

    public <R> R queryOne(SqlBuilder oldSqlBuilder, Class<R> resultClazz) {
        Query query = createQuery(oldSqlBuilder);
        Map<String, Object> resultMap = (Map<String, Object>) query.getSingleResult();
        return AppJsons.convertUnderlineMap(resultMap, resultClazz);
    }

    public <R> List<R> queryList(SqlBuilder oldSqlBuilder, Class<R> resultCLazz) {
        Query query = createQuery(oldSqlBuilder);
        List<Map<String, ?>> resultList = query.getResultList();
        return AppJsons.convertUnderlineMapList(resultList, resultCLazz);
    }

    public <R> AppPageResult<R> queryPage(SqlBuilder sqlBuilder, Class<R> resultClazz, Integer pageIndex, Integer pageSize) {
        AppPages.checkPageParam(pageIndex, pageSize);

        String countSql = "select count(1) from (" + sqlBuilder.toString() + ") as total";
        Query countQuery = entityManager.createNamedQuery(countSql).unwrap(org.hibernate.query.Query.class);
        sqlBuilder.getParams().forEach(countQuery::setParameter);
        long count = (Long) countQuery.getSingleResult();

        String limitSegment = " limit " + pageSize + " offset " + (pageIndex - 1) * pageSize;

        Query contentQuery = entityManager.createNamedQuery(sqlBuilder + limitSegment);
        sqlBuilder.getParams().forEach(contentQuery::setParameter);
        contentQuery.unwrap(NativeQuery.class).setResultListTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, ?>> resultList = contentQuery.getResultList();
        List<R> content = AppJsons.convertUnderlineMapList(resultList, resultClazz);
        return AppPageResult.of(count, content);
    }
}
