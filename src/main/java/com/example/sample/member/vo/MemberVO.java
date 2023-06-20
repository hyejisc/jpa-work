package com.example.sample.member.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class MemberVO {

    private Long id;
    private String name;
    private String address;
    private LocalDateTime createDate;
}
