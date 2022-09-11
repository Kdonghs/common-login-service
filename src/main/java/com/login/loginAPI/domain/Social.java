package com.login.loginAPI.domain;

import lombok.Getter;

@Getter
public enum Social {
    LOCAL("LOCAL"), KAKAO("KAKAO"), GOOGLE("GOOGLE");

    private String value;

    Social(String value) {
        this.value = value;
    }
}
