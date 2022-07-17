package com.login.loginAPI.repository.Impl;

import com.login.loginAPI.domain.Member;
import com.login.loginAPI.repository.SearchMemberRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class SearchMemberRepositoryImpl implements SearchMemberRepository {
    private final EntityManager em;
    @Override
    public boolean searchId(String id, String pw) {

        try {
            String jpql = "select m from Member as m where m.id = '" + id + "' and m.password = '"+ pw + "'";
            List<Member> result  = em.createQuery(jpql,Member.class)
                    .setFirstResult(1)
                    .getResultList();
            System.out.println("result = " + result.size());
            if (result.size()!=0){
                return true;
            }
        }catch (Exception e){

        }finally {
            em.close();
        }
        return false;
    }

}
