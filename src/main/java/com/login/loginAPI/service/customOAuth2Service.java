/*
package com.login.loginAPI.service;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.dto.OAuthAttributes;
import com.login.loginAPI.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(request);

        String registrationId = request.getClientRegistration().getRegistrationId();
        String userNameAttributeName = request.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        */
/*Member member = saveOrUpdate(attributes);*//*


        return oAuth2User;
    }

    */
/*private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findMemberByEmailContaining(attributes.getEmail()).stream()
                .map(entity -> entity)
                .orElse(attributes.toEntity());

        return memberRepository.save(member);
    }*//*

}
*/
