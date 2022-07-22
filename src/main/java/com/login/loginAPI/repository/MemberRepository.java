package com.login.loginAPI.repository;

import com.login.loginAPI.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    Optional<Member> findByIdAndPasswordAndRoleType(String id, String password, Enum roleType);

    Optional<Member> findByNameAndEmail(String name,String email);
    Optional<Member> findByNameAndEmailAndId(String name,String email,String id);

}
