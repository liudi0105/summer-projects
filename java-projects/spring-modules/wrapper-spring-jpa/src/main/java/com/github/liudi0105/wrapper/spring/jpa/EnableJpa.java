package com.github.liudi0105.wrapper.spring.jpa;

import org.springframework.context.annotation.Import;

@Import(JpaConfiguration.class)
public @interface EnableJpa {
}
