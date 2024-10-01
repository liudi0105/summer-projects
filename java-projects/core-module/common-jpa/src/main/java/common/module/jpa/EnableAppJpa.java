package common.module.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.config.DefaultRepositoryBaseClass;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JpaConfiguration.class)
@EnableJpaAuditing
@EntityScan
@EnableJpaRepositories
public @interface EnableAppJpa {
    @AliasFor(annotation = EnableJpaRepositories.class)
    Class<?> repositoryFactoryBeanClass() default JpaRepositoryFactoryBean.class;

    @AliasFor(annotation = EnableJpaRepositories.class)
    Class<?> repositoryBaseClass() default DefaultRepositoryBaseClass.class;
}
