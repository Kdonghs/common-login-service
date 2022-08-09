package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class loginController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/login")
    public String login(Member member, Model model){
        String flag = memberService.loginOk(member.getId(),member.getPassword());
        if (flag!=""){
            model.addAttribute("name",flag + " 로그인됨");
        }
        return "home";
    }

}
