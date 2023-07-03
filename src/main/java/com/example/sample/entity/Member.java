package com.example.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate // 변경값만 업데이트
@EntityListeners(AuditingEntityListener.class) // createdDate 어노테이션 사용에 필요
public class Member {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String address;

    @CreatedDate //insert될때 현재시간 저장
    LocalDateTime createDate;

}
