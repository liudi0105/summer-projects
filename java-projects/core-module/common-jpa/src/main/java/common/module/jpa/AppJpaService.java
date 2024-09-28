package common.module.jpa;

import common.module.model.dto.BaseDTO;
import common.module.util.AppJsons;
import common.module.util.AppReflections;
import common.module.util.SerializableFunction;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.EntityInformation;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class AppJpaService<R extends SimpleJpaRepo, E, I, D extends BaseDTO> implements JpaService {

    @Getter
    private R repo;

    private EntityInformation<E, I> entityInformation;

    protected final Class<D> dtoClass = computeDtoClass();

    protected final Class<E> entityClass = computeEntityClass();

    protected final Class<R> repoClass = computeRepoClass();

    protected boolean convertWithJson;

    @Autowired
    public void setRepo(R repo) {
        this.repo = repo;
    }

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
