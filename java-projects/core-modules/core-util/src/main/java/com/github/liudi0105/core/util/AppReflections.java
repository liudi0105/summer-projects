package com.github.liudi0105.core.util;

import com.github.liudi0105.spring.error.AppError;
import com.github.liudi0105.spring.jpa.SerializableFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppReflections {
    public static final String WRITE_REPLACE = "writeReplace";
    private static final Map<SerializableFunction<?, ?>, Field> fieldCache = new ConcurrentHashMap<>();
    private static final Map<SerializableFunction<?, ?>, String> fieldNameCache = new ConcurrentHashMap<>();

    public static String getFieldName(SerializableFunction<?, ?> function) {
        return fieldNameCache.computeIfAbsent(function, AppReflections::findFieldName);
    }

    public static Field getField(SerializableFunction<?, ?> function) {
        return fieldCache.computeIfAbsent(function, AppReflections::getField);
    }

    public static <E> Class<E> clazz(String className) {
        try {
            return (Class<E>) Class.forName(className);
        } catch (Exception e) {
            throw new AppError(e);
        }
    }

    public static <E> E newInstance(String className) {
        try {
            return newInstance(clazz(className));
        } catch (Exception e) {
            throw new AppError(e);
        }
    }

    public static <E> E newInstance(Class<E> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new AppError(e);
        }
    }

    public static <E, V> void setProperty(SerializableFunction<E, V> prop, E e, V value) {
        if (e == null) {
            return;
        }
        String fieldName = getFieldName(prop);
        setField(e, fieldName, value);
    }

    public static void setField(Object target, String fieldName, Object vlaue) {
        try {
            Field declaredField = target.getClass().getDeclaredField(fieldName);
            ReflectionUtils.makeAccessible(declaredField);
            ReflectionUtils.setField(declaredField, target, vlaue);
        } catch (Exception e) {
            throw new AppError(e);
        }
    }

    private static String findFieldName(SerializableFunction<?, ?> function) {
        try {
            Method method = function.getClass().getDeclaredMethod(WRITE_REPLACE);
            ReflectionUtils.makeAccessible(method);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);

            String implMethodName = serializedLambda.getImplMethodName();
            String fieldName;
            if (implMethodName.startsWith("get") && implMethodName.length() > 3) {
                fieldName = Introspector.decapitalize(implMethodName.substring(3));
            } else if (implMethodName.startsWith("is") && implMethodName.length() > 2) {
                fieldName = Introspector.decapitalize(implMethodName.substring(2));
            } else if (implMethodName.startsWith("lambda$")) {
                throw new IllegalArgumentException("lambda is not support");
            } else {
                throw new IllegalArgumentException(implMethodName + ": not Getter method");
            }
            return fieldName;
        } catch (Exception e) {
            throw new AppError(e);
        }
    }

    private static Field findField(SerializableFunction<?, ?> function) {
        Field field = null;
        try {
            Method method = function.getClass().getDeclaredMethod(WRITE_REPLACE);
            ReflectionUtils.makeAccessible(method);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
            String declaredClass = serializedLambda.getImplClass().replace("/", ".");
            Class<?> clazz = Class.forName(declaredClass, false, ClassUtils.getDefaultClassLoader());
            String fieldName = getFieldName(function);
            field = ReflectionUtils.findField(clazz, fieldName);
            if (field == null) {
                throw new NoSuchFieldException(fieldName);
            }
            return field;
        } catch (Exception e) {
            throw new AppError(e);
        }
    }

    public static <T> Class<T> nthGenericArgClass(Class<?> subClazz, int index) {
        ParameterizedType parameterizedType = (ParameterizedType) subClazz.getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[index];
    }
}
