package common.module.jpa;

import common.module.util.AppJsons;
import common.module.util.AppReflections;
import common.module.util.SerializableFunction;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.EntityInformation;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class AppJpaService<R extends BaseJpaRepo<E, D>, E extends BaseJpaPO, D extends BaseDTO> implements JpaService {

    @Autowired
    @Getter
    private R repo;

    private EntityInformation<E, String> entityInformation;

    public final Class<D> dtoClass = computeDtoClass();

    public final Class<E> entityClass = computeEntityClass();

    protected final Class<R> repoClass = computeRepoClass();

    protected boolean convertWithJson;

    protected void useBeanUtils() {
        this.convertWithJson = false;
    }

    String toName(SerializableFunction<E, ?> function) {
        return AppReflections.getFieldName(function);
    }

    Class<D> computeDtoClass() {
        Type clazz = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz;

        Class<D> actualTypeArgument = (Class<D>) parameterizedType.getActualTypeArguments()[2];
        if (String.class.equals(actualTypeArgument)) {
            return (Class<D>) parameterizedType.getActualTypeArguments()[3];
        }
        return actualTypeArgument;
    }

    Class<E> computeEntityClass() {
        ParameterizedType clazz = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) clazz.getActualTypeArguments()[1];
    }

    Class<R> computeRepoClass() {
        ParameterizedType clazz = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<R>) clazz.getActualTypeArguments()[0];
    }

    public E e(D dto) {
        return AppJsons.convert(dto, entityClass);
    }
}
