package com.login.loginAPI.domain;

import com.login.loginAPI.entity.BaseTimeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberKey;

    @Column(name = "ID")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    private Integer age;

    @Column(name = "Email")
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Enumerated(EnumType.STRING)
    private Social social;

    @Lob
    private String description;


    public Member(String ID, String password, String name, Integer age, String email, RoleType roleType,Social social, String description) {
        this.id = ID;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.roleType = roleType;
        this.social = social;
        this.description = description;
    }

    public Member() {

    }
}
