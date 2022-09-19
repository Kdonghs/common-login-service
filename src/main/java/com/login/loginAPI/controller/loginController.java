package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.domain.RoleType;
import com.login.loginAPI.domain.SNSInfo;
import com.login.loginAPI.domain.Social;
import com.login.loginAPI.service.CustomOAuth2Service;
import com.login.loginAPI.service.MemberService;
import com.login.loginAPI.service.loginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class loginController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private loginService loginService;
    @Autowired
    private CustomOAuth2Service customOAuth2Service;

    /*@RequestMapping("/login") @SessionAttribute 으로 처리
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
    }*/

    @PostMapping("/createAccount.do")
    public String createAccount(Member member){
        member.setRoleType(RoleType.USER);

        if (!memberService.emailMember(member.getEmail()).isEmpty()){
            Member flag = memberService.emailMember(member.getEmail()).get();

            flag.update(member.getPassword(),member.getAge(),member.getUsername());

            loginService.EncodingPassword(flag);
        }else {
            loginService.EncodingPassword(member);
        }


        return "redirect:/";
    }

    @PostMapping(value = "/id/check")
    @ResponseBody
    public ResponseEntity<?> checkIdDuplication(@RequestParam(value = "id") String id) throws loginController.BadRequestException {
        System.out.println("id = " + id);
        System.out.println(memberService.existsByMemberId(id));

        if (memberService.existsByMemberId(id) == true) {
            throw new loginController.BadRequestException("이미 사용중인 아이디 입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 아이디 입니다.");
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already exists")
    public class BadRequestException extends RuntimeException {

        public BadRequestException(String message) {
            super(message);
        }
    }

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
        Optional<Member> flag = memberService.searchPw(member.getName(), member.getEmail(),member.getUsername());
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
