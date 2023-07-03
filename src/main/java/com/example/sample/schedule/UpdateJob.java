package com.example.sample.schedule;

import com.example.sample.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateJob {

    @Autowired
    private MemberMapper memberMapper;

    @Scheduled(cron="0 * * * * *") //1분주기로
    public void executeTask() throws Exception {

        memberMapper.updateName();

    }

}
