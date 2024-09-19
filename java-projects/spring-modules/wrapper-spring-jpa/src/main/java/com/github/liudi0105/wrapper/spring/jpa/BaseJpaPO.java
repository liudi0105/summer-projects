package com.github.liudi0105.wrapper.spring.jpa;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseJpaPO {
    @Id
    @UuidGenerator
    private String id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean deleted = false;

    @Version
    private Long version;
}
