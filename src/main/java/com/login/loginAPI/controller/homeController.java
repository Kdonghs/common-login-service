package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class homeController {
    @GetMapping("/")
    public String home(@SessionAttribute(name = sessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        if (loginMember == null){
            return "login/loginForm";
        }

        // 세션에 회원 데이터가 있으면 로그인한 유저를 위한 홈 화면으로 이동
        model.addAttribute("member", loginMember);
        return "home";
    }

    @GetMapping("login/loginForm")
    public String loginForm(){
        return "login/loginForm";
    }
    @GetMapping("createAccount/createAccount")
    public String createAccount(){
        return "createAccount/createAccount";
    }
    @GetMapping("searchAccount/searchID")
    public String searchID(){
        return "searchAccount/searchID";
    }
    @GetMapping("searchAccount/searchPW")
    public String searchPW(){
        return "searchAccount/searchPW";
    }
    @GetMapping("/item/createItem")
    public String createItem(){
        return "item/createItem";
    }
}
