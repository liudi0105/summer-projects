package com.github.liudi0105.helidon.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
    @Column
    private String username;
    @Column
    private String nameCn;
}
