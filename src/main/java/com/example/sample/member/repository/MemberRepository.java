package com.example.sample.member.repository;

import com.example.sample.member.dto.MemberDto;
import com.example.sample.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
