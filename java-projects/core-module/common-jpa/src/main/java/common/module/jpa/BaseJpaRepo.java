package common.module.jpa;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BaseJpaRepo<E>extends AppJpaRepo<E, String>  {

}
