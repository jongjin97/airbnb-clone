package com.example.airbnb.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public class EntityTime {
    @CreatedDate
    LocalDate regTime;
    @LastModifiedDate
    LocalDate updateTime;
}
