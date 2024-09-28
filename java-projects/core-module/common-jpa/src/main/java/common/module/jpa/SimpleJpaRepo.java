package common.module.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SimpleJpaRepo<E, I> extends JpaRepository<E, I>, JpaSpecificationExecutor<E> {
}
