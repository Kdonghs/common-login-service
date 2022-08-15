package com.login.loginAPI.controller;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@Controller
@RequestMapping("/createAccount")
@RequiredArgsConstructor
public class createAccountController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/createAccountAction")
    public String createAccount(Member member){
        Date day = new Date();
        member.setCreatedDate(day);
        member.setLastModifiedDate(day);

        if (memberService.createMember(member)){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
        return "home";
    }

    @RequestMapping( value = "/id/check.json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> checkIdDuplication(@RequestParam(value = "id") String Id) throws BadRequestException {

        if (memberService.existsByMemberId(Id) == true) {
            throw new BadRequestException("이미 사용중인 아이디 입니다.");
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
}
