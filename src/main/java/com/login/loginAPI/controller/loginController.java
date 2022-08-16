package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class loginController {

    @Autowired
    private MemberService memberService;
    sessionController session = new sessionController();

    @RequestMapping("/login")
    public String login(Member member, BindingResult bindingResult, HttpServletResponse response){

        System.out.println("member.get() = " + member.getSocial());
        String flag = memberService.loginOk(member.getId(),member.getPassword());
        if(flag==null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        } else {
            session.createSession(member,response);
            return "home";
        }
    }

    @RequestMapping("/logOut")
    public String logout(HttpServletRequest request) {
        session.expires(request);
        return "login/loginForm";
    }


}
