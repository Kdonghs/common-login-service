package com.login.loginAPI.repository;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    Optional<Member> findMemberByRoleTypeAndIdAndPassword(@Param(value = "roleType") RoleType roleType,String id,String password);
    Optional<Member> findMemberByNameAndEmail(String name,String email);
    Optional<Member> findMemberByNameAndEmailAndId(String name,String email,String id);

    List<Member> findMemberByNameContaining(String search);
    List<Member> findMemberByAgeLike(int search);
    List<Member> findMemberByEmailContaining(String search);

    boolean existsById(String memberId);
}
