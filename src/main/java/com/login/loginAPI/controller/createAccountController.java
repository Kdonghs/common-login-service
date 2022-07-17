package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/createAccount")
@RequiredArgsConstructor
public class createAccountController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/createAccountAction")
    public String createAccount(Member member){
        Date day = new Date();
        member.setCreatedDate(day);
        member.setLastModifiedDate(day);

        if (memberService.createMember(member)){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
        return "home";
    }
}
