package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
public class homeController {
    @Autowired
    private MemberService memberService;

    /*@GetMapping("/") //spring security x
    public String home(@SessionAttribute(name = sessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if (loginMember == null){
            return "login/loginForm";
        }

        // 세션에 회원 데이터가 있으면 로그인한 유저를 위한 홈 화면으로 이동
        model.addAttribute("member", loginMember);
        return "home";
    }*/

    @GetMapping("/")
    public String home(Authentication authentication, Model model){
        if (authentication==null){
            return "login/loginForm";
        }

        Member member = memberService.member(authentication.getName()).get();
        model.addAttribute(member);
        System.out.println(authentication);
        return "home";
    }
    @GetMapping("login/loginForm")
    public String loginForm(){
        return "login/loginForm";
    }
    @GetMapping("login/createAccount")
    public String createAccount(){return "login/createAccount";}
    @GetMapping("login/searchID")
    public String searchID(){
        return "login/searchID";
    }
    @GetMapping("login/searchPW")
    public String searchPW(){
        return "login/searchPW";
    }
    @GetMapping("/sidebar/404")
    public String notfound(){return "/sidebar/404";}
    @GetMapping("/item/createItem")
    public String createItem(){
        return "item/createItem";
    }
}
