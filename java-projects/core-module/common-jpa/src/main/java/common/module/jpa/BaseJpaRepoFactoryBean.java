package common.module.jpa;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class BaseJpaRepoFactoryBean<R extends BaseJpaRepo<T, ?>, T extends BaseJpaPO> extends JpaRepositoryFactoryBean<R, T, String> {
    private Class<?> repositoryInterface;

    public BaseJpaRepoFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
        this.repositoryInterface = repositoryInterface;
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager) {

            @Override
            protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
                JpaEntityInformation entityInformation = getEntityInformation(information.getDomainType());
                return new JpaRepositoryImpl<>(entityInformation, entityManager, repositoryInterface);
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return JpaRepositoryImpl.class;
            }
        };
    }
}
