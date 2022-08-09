package com.login.loginAPI.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "members")
public class Member {
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

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
