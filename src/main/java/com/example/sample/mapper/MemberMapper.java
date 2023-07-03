package com.example.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void updateName();
}
