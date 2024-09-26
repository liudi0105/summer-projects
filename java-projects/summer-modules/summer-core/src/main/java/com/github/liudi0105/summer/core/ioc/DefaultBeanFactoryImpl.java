package common.module.summer.core.ioc;

import common.module.summer.core.ioc.exceptions.BeanAlreadyDefinedException;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultBeanFactoryImpl implements BeanFactory {

    private final Map<String, Object> beanMap = new HashMap<>();
    private final MultiValuedMap<Class<?>, Object> beanClazzMap = new HashSetValuedHashMap<>();

    public void registerBean(String beanName, Object object) {
        if (beanMap.containsKey(beanName)) {
            throw new BeanAlreadyDefinedException();
        }
        beanMap.put(beanName, object);
        beanClazzMap.put(object.getClass(), object);
    }

    public void registerBean(Class<?> beanClazz, Object object) {
        registerBean(beanClazz.getSimpleName(), object);
    }

    public <T> T getBean(String beanName) {
        return (T) beanMap.get(beanName);
    }

    public <T> Optional<Object> getBean(Class<T> clazz) {
        return beanClazzMap.get(clazz).stream().findFirst();
    }

}
