package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
@Controller
@RequestMapping("/profile")
public class ProfileController {
    final String REPLACE = "**********";

    @Autowired
    MemberService memberService;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model){
        Member member = memberService.member(authentication.getName()).get();
        member.setPassword(REPLACE);
        model.addAttribute("member",member);

        return  "profile/profile";
    }
    @GetMapping("/editProfile")
    public String editProfile(Authentication authentication, Model model){
        Member member = memberService.member(authentication.getName()).get();
        model.addAttribute("member",member);
        return  "profile/editProfile";
    }

    @PostMapping ("/editProfile.do")
    public String editProfileDo(@RequestParam(value = "newPass",defaultValue = "") String newPass,
                                Authentication authentication,Member member, Model model){

        Member flag = memberService.member(authentication.getName()).get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(member.getPassword());
        System.out.println(encoder.matches(member.getPassword(), flag.getPassword()));
        if (!encoder.matches(member.getPassword(), flag.getPassword())){
            flag.setPassword(REPLACE);
            model.addAttribute("member", flag);
            return  "profile/profile";
        }

        flag.setId(member.getId());
        flag.setPassword(encoder.encode(newPass));
        flag.setDescription(member.getDescription());

        memberService.memberSave(flag);
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<10;i++){
            sb.append('*');
        }
        System.out.println(sb);
        flag.setPassword(sb.toString());

        model.addAttribute("member", flag);
        return  "profile/profile";
    }
}
