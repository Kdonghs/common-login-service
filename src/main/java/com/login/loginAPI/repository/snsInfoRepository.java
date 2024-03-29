package com.login.loginAPI.repository;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.domain.SNSInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface snsInfoRepository extends JpaRepository<SNSInfo,String> {
    Optional<SNSInfo> findSNSInfoBySnsID(String sub);
    Optional<SNSInfo> findSNSInfoByMemberId(Member memberid);
}
