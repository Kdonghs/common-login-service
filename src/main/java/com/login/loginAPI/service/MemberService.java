package com.login.loginAPI.service;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.domain.RoleType;
import com.login.loginAPI.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public boolean createMember(Member member){
        try {
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public String loginOk(String id, String pw){
        Optional<Member> flag = memberRepository.findByIdAndPasswordAndRoleType(id, pw, RoleType.USER);
        if (flag.isEmpty()){
            return "";
        }else {
            return flag.get().getName();
        }

    }

    public Optional<Member> searchId(String name, String email){
        return memberRepository.findMemberByNameAndEmail(name,email);
    }

    public Optional<Member> searchPw(String name, String email, String id){
        return memberRepository.findMemberByNameAndEmailAndId(name,email,id);
    }

    public List<Member> memberAll(){
        return memberRepository.findAll();
    }

    public List<Member> searchMemberAge(int search){
        return memberRepository.findMemberByAgeLike(search);
    }
    public List<Member> searchMemberName(String search){
        return memberRepository.findMemberByNameContaining(search);
    }
    public List<Member> searchMemberEmail(String search){
        return memberRepository.findMemberByEmailContaining(search);
    }

}
