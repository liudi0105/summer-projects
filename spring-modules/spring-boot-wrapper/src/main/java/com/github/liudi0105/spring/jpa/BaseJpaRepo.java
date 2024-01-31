package com.github.liudi0105.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseJpaRepo<T> extends JpaRepository<T, String> {
}
