package com.login.loginAPI.repository;

import com.login.loginAPI.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findItemByNameContaining(String search);
    List<Item> findItemByPriceLike(int search);

    @Override
    Optional<Item> findById(Long aLong);
}
