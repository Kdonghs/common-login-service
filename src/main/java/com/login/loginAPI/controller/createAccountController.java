package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.domain.RoleType;
import com.login.loginAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/createAccount")
@RequiredArgsConstructor
public class createAccountController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/createAccountAction")
    public String createAccount(Member member){
        if (memberService.createMember(member)){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
        return "home";
    }
}
