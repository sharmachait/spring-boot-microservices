package com.sharmachait.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseEntity {
//    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
//    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
//    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
//    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
