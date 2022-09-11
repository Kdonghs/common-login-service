package com.login.loginAPI.domain;

import com.login.loginAPI.entity.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "`item`")
public class Item extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer volume;

    @Lob
    private String description;

    @ManyToOne(optional = false)
    @JoinTable(name = "member_item", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "memberID"))
    private Member memberId;

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Item(){}
}
