package com.example.springaclsecurity.model.entities;


import com.example.springaclsecurity.constants.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @CreatedBy
    @JoinColumn(
            name = "`created_by`",
            nullable = false,
            foreignKey = @ForeignKey(name = "`fk_created_user`"),
            updatable = false
    )
    private User createdBy;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @LastModifiedBy
    @JoinColumn(
            name = "`updated_by`",
            nullable = false,
            foreignKey = @ForeignKey(name = "`fk_updated_user`")
    )
    private User updatedBy;
}
