package com.login.loginAPI.domain;

import com.login.loginAPI.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "`member`")
@NoArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Lob
    private String description;

    @OneToMany
    private List<SNSInfo> snsInfo = new ArrayList<SNSInfo>();

    @OneToMany
    private List<Item> Item = new ArrayList<Item>();


    @Builder
    public Member(String username, String password, String name, Integer age, String email, RoleType roleType,Social social, String description) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.roleType = roleType;
        this.description = description;
    }

}
