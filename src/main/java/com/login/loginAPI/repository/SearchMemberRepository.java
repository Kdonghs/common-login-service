package com.login.loginAPI.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface SearchMemberRepository {
    boolean searchId(String id,String pw);
}
