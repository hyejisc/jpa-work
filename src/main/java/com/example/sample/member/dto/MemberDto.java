package com.example.sample.member.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String name;
    private String address;

    private String searchOption;

    private String keyword;
}
