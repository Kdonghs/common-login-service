package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/memberList")
public class memberListController {

    @Autowired
    MemberService memberService;

    @GetMapping("/memberList")
    public String memberList(Model model){
        List<Member> members = memberService.memberAll();
        model.addAttribute("members",members);
        return "memberList/memberList";
    }
}
