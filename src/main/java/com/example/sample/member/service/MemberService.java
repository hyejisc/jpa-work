package com.example.sample.member.service;

import com.example.sample.member.dto.MemberDto;
import com.example.sample.member.entity.Member;
import com.example.sample.member.entity.QMember;
import com.example.sample.member.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

import static com.example.sample.member.entity.QMember.member;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public Member createMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }


    public List<Member> getMemberList(MemberDto dto) {

       return memberRepository.findAll(); //전부 다 가져옴

    }

    public Long getMemberListCnt() {
        return memberRepository.count();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null); // 결과 값이 없을때 null 리턴
    }


    public Member updateMember(Member member, Long id) {
        Member updatedMember = null;

        Member existMember = memberRepository.findById(id).orElse(null);

        if (existMember != null) {
            updatedMember = memberRepository.save(member);
        }

        return updatedMember;
    }


}
