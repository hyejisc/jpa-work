package com.example.sample.member.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long memberId;
    private String name;
    private String address;

    private String searchOption;
}
