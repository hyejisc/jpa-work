package com.example.sample.member;

import com.example.sample.member.dto.MemberDto;
import com.example.sample.member.entity.Member;
import com.example.sample.member.service.MemberService;
import com.example.sample.member.vo.MemberVO;
import com.querydsl.core.QueryResults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;


    // 멤버 인서트
    @PostMapping("/create")
    public ResponseEntity<Member> createMember(MemberDto dto) throws ParseException {

        // 방법1
//        Member member = Member.builder()
//                .name(dto.getName())
//                .address(dto.getAddress())
//                //.createDate(LocalDateTime.now())  -> createdDate 어노테이션으로 대체
//                .build();


        // 방법2 ModelMapper : 서로 다른 객체간의 필드값을 자동으로 매핑
        Member member = new ModelMapper().map(dto, Member.class);

        Member savedMember = memberService.createMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    // 멤버 전체 리스트 조회
    @GetMapping("/list")
    public Map<String, Object> getMemberList(MemberDto dto, Pageable pageable) throws Exception{

        Map<String, Object> res = new HashMap<>();

        List<MemberVO> members = memberService.getMemberList(dto, pageable);
        Integer membersCnt = memberService.getMemberListCnt(dto, pageable);

        res.put("data", members);
        res.put("dataCnt", membersCnt);
        return res;

    }

    // 멤버 한명 조회
    @GetMapping("/{id}")
    public Map<String, Object> getMember(@PathVariable("id") Long id) {

        Map<String, Object> res = new HashMap<>();

        Member member = memberService.getMember(id);

        if (member != null) {
            res.put("data", member);
            res.put("res", "success");
        } else res.put("res", "fail");

        return res;
    }


    // 멤버 수정
    @PostMapping("/update/{id}")
    public Map<String, Object> updateMember(MemberDto dto, @PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();

        Member member = Member.builder()
                .id(id) // id 기준으로 찾고 있으면 update, 없으면 insert
                .name(dto.getName())
                .address(dto.getAddress())
                .build();

        Member updatedMember = memberService.updateMember(member, id);
        if (updatedMember != null) res.put("res", "success");
        else res.put("res", "fail"); //일치하는 아이디 없음

        return res;
    }


}
