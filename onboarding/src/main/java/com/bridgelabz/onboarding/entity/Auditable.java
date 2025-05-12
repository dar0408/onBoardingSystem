package com.bridgelabz.onboarding.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedBy
    @Column(updatable = false)
    @JsonProperty("createdBy")
    private String createdBy;

    @CreatedDate
    @Column(updatable = false)
    @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    @LastModifiedBy
    @JsonProperty("lastModifiedBy")
    private String lastModifiedBy;

    @LastModifiedDate
    @JsonProperty("lastModifiedDate")
    private LocalDateTime lastModifiedDate;


}