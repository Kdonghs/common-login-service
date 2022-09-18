package com.login.loginAPI.service;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.domain.RoleType;
import com.login.loginAPI.domain.SNSInfo;
import com.login.loginAPI.domain.Social;
import com.login.loginAPI.dto.OAuthAttributes;
import com.login.loginAPI.dto.SessionMember;
import com.login.loginAPI.repository.MemberRepository;
import com.login.loginAPI.repository.snsInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final snsInfoRepository snsInfoRepository;
    private final HttpSession httpSession;
    private final MemberService memberService;

    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(request);
        System.out.println(oAuth2User);

        String registrationId = request.getClientRegistration().getRegistrationId();
        String userNameAttributeName = request.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        System.out.println(registrationId);
        System.out.println(userNameAttributeName);

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Member member = saveOrUpdate(attributes,registrationId);
        httpSession.setAttribute("snsInfo", new SessionMember(member));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(member.getRoleType().getValue())),
                attributes.getAttributes(),attributes.getNameAttributeKey());
    }

private Member saveOrUpdate(OAuthAttributes attributes, String registrationId) {
    Optional<Member> flag;
    if (!snsInfoRepository.findSNSInfoBySnsID(attributes.getSub()).isEmpty()){
        Optional<SNSInfo> a  = snsInfoRepository.findSNSInfoBySnsID(attributes.getSub());
        flag = memberRepository.findById(a.get().getMemberId().getId());
        return flag.get();
    }
    flag = memberRepository.findMemberByEmail(attributes.getEmail());
    SNSInfo snsInfo = new SNSInfo(attributes.getSub(),attributes.getName(), attributes.getPicture());

    if(registrationId.equals("google")){
        snsInfo.setSnsType(Social.GOOGLE);
    }
    Member member;

    if (flag.isEmpty()){
        member = new Member();
        member.setEmail(attributes.getEmail());
        member.setName(attributes.getName());
        member.setRoleType(RoleType.USER);
        memberRepository.save(member);

        snsInfo.setMemberId(memberRepository.findMemberByEmail(attributes.getEmail()).get());
        snsInfoRepository.save(snsInfo);

        return member;
    }else {
        member = flag.get();
        snsInfo.setMemberId(member);
        snsInfoRepository.save(snsInfo);
    }

        return memberRepository.save(member);
    }

    public Optional<SNSInfo> snsInfo(String sub){
        return snsInfoRepository.findSNSInfoBySnsID(sub);
    }
    public Optional<SNSInfo> memberid(Member memberid){
        return snsInfoRepository.findSNSInfoByMemberId(memberid);
    }
    public void snsinfoSave(SNSInfo snsInfo){
        snsInfoRepository.save(snsInfo);
    }

    public Member authenticationMember(Authentication authentication ){
        Member member;
        if (memberService.member(authentication.getName()).isEmpty()){
            OAuth2AuthenticationToken token =(OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            Map<String, Object> attributes = token.getPrincipal().getAttributes();
            Optional<SNSInfo> flag = snsInfo((String) attributes.get("sub"));
            member=memberService.member(flag.get().getMemberId().getId()).get();
        }else {
            member = memberService.member(authentication.getName()).get();
        }
        return member;
    }

}
