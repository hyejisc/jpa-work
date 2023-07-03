package com.example.sample.service;

import com.example.sample.dto.MemberDto;
import com.example.sample.entity.Member;
import com.example.sample.repository.MemberRepository;
import com.example.sample.vo.MemberVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.sample.entity.QMember.member;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private final JPAQueryFactory jpaQueryFactory;

    public MemberService(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public Member createMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }


    public List<MemberVO> getMemberList(MemberDto dto, Pageable pageable) {

        // return memberRepository.findAll(); //전부 다 가져옴
       // return  memberRepository.findAll(pageable).toList();

        BooleanBuilder builder = new BooleanBuilder();
        String keyword = dto.getKeyword();

        if(keyword!= null) {

            switch (dto.getSearchOption()){
                case "name" :
                    builder.and(member.name.contains(dto.getKeyword()));
                    break;
                case "address" :
                    builder.and(member.address.contains(dto.getKeyword()));
                    break;
                }
        }

        return  jpaQueryFactory.select(Projections.constructor(MemberVO.class,
                member.id, member.address, member.name, member.createDate))
                .from(member)
                .where(builder)
                .orderBy(member.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public Integer getMemberListCnt(MemberDto dto, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();
        String keyword = dto.getKeyword();

        if(keyword!= null) {

            switch (dto.getSearchOption()){
                case "name" :
                    builder.and(member.name.contains(dto.getKeyword()));
                    break;
                case "address" :
                    builder.and(member.address.contains(dto.getKeyword()));
                    break;
            }
        }

        return Math.toIntExact(jpaQueryFactory.select(Projections.constructor(MemberVO.class,
                        member.id, member.address, member.name, member.createDate))
                .from(member)
                .where(builder)
                .orderBy(member.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().size());

       // return memberRepository.count();
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
