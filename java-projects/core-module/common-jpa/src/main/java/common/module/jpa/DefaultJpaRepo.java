package common.module.jpa;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DefaultJpaRepo<E>extends SimpleJpaRepo<E, String> {

}
