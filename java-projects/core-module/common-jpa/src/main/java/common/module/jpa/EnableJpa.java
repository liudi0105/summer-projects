package common.module.jpa;

import org.springframework.context.annotation.Import;

@Import(JpaConfiguration.class)
public @interface EnableJpa {
}
