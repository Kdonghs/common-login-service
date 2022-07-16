package com.login.loginAPI.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Table(name = "member")
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

    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member() {
    }

    public Member(String ID, String password, String name, Integer age, String email, RoleType roleType, String description) {
        this.id = ID;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.roleType = roleType;
        this.description = description;
    }

    public void setMemberKey(Long memberKey) {
        this.memberKey = memberKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String ID) {
        this.id = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
