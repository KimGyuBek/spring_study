package com.example.testproject.data.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class}) //이 entity가 사용되는 시점에서 업데이트 이벤트 받아들여서 특정 행동을 해준다.
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;

//    @CreatedBy
//    @Column(updatable = false)
//    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @LastModifiedBy
//    private String updatedBy;

}
