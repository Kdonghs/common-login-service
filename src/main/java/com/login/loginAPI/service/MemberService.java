package com.login.loginAPI.service;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean loginOk(String id, String pw){

        try {
            System.out.println(memberRepository.searchId(id, pw));
            return memberRepository.searchId(id,pw);
        }catch (Exception e){
            return false;
        }
    }

    public Optional<Member> getMember(Long id){
        return memberRepository.findById(id);
    }


    public Member updateMember(Long id, Member member){
        final Optional<Member> targetMember = memberRepository.findById(id);
        if (targetMember.isPresent()){
            member.setMemberKey(id);
            return memberRepository.save(member);
        }else {
            return null;
        }
    }

    public Member patchMember(Long id, Member member){
        final Optional<Member> targetMember = memberRepository.findById(id);
        if (targetMember.isPresent()){
            if (member.getAge() != null){
                targetMember.get().setAge(member.getAge());
            }
            if (member.getName() != null){
                targetMember.get().setName(member.getName());
            }
            if (member.getRoleType() != null){
                targetMember.get().setRoleType(member.getRoleType());
            }
            return memberRepository.save(targetMember.get());
        }else {
            return null;
        }
    }

    public boolean deleteMember(Long id){
        final Optional<Member> targetMember = memberRepository.findById(id);
        if (targetMember.isPresent()){
            memberRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public List<Member> memberAll(){
        return memberRepository.findAll();
    }

}
