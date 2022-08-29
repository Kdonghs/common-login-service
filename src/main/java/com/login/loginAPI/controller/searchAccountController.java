/*
package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class searchAccountController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/searchIDAction")
    public String searchId(Member member, Model model){
        Optional<Member> flag = memberService.searchId(member.getName(), member.getEmail());
        if (flag.isEmpty()){
            model.addAttribute("search","아이디 검색 결과");
            model.addAttribute("result","검색결과 없음");
            return "searchAccount/searchResult";
        }
        model.addAttribute("search","아이디 검색 결과");
        model.addAttribute("result",flag.get().getId());
        return "searchAccount/searchResult";
    }

    @RequestMapping("/searchPwAction")
    public String searchPw(Member member, Model model){
        System.out.println("member.getId() = " + member.getId());
        Optional<Member> flag = memberService.searchPw(member.getName(), member.getEmail(),member.getId());
        if (flag.isEmpty()){
            model.addAttribute("search","비밀번호 검색 결과");
            model.addAttribute("result","검색결과 없음");
            return "searchAccount/searchResult";
        }
        model.addAttribute("search","비밀번호 검색 결과");
        model.addAttribute("result",flag.get().getPassword());
        return "searchAccount/searchResult";
    }
}
*/
