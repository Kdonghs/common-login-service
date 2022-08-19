package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class loginController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/login")
    public String login(Member member, BindingResult bindingResult, HttpServletRequest request){

        if (bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Optional<Member> flag = memberService.loginOk(member.getId(),member.getPassword(),"USER");

        if(flag.get()==null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute(sessionConst.LOGIN_MEMBER, flag.get());
        return "redirect:/";
    }

    @RequestMapping("/logOut")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return "redirect:/";
    }


}
