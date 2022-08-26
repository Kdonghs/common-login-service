package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    MemberService memberService;

    @GetMapping("/profile")
    public String profile(@SessionAttribute(name = sessionConst.LOGIN_MEMBER, required = false) Member member, Model model){
        Member flag = memberService.member(member.getMemberKey()).get();
        model.addAttribute("member", flag);
        return  "profile/profile";
    }
    @GetMapping("/editProfile")
    public String editProfile(@SessionAttribute(name = sessionConst.LOGIN_MEMBER, required = false) Member member, Model model){
        Member flag = memberService.member(member.getMemberKey()).get();
        model.addAttribute("member", flag);
        return  "profile/editProfile";
    }
    @PostMapping ("/editProfile.do")
    public String editProfileDo(Member member, Model model){
        System.out.println(member);
        Date day = new Date();
        Member flag = memberService.member(member.getMemberKey()).get();

        flag.setName(member.getName());
        flag.setEmail(member.getEmail());
        flag.setId(member.getId());
        flag.setPassword(member.getPassword());
        flag.setDescription(member.getDescription());
        flag.setLastModifiedDate(day);

        memberService.memberSave(flag);

        model.addAttribute("member", flag);
        return  "profile/profile";
    }
}
