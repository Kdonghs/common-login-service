package com.login.loginAPI.domain;

import com.login.loginAPI.entity.BaseTimeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class SNSInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String snsID;

    @Enumerated(EnumType.STRING)
    private Social snsType;

    private String snsName;

    private String snsProfile;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public SNSInfo update(String name, Social snsType, String snsProfile) {
        this.snsName = name;
        this.snsType = snsType;
        this.snsProfile = snsProfile;

        return this;
    }

    public SNSInfo(String snsID, String snsName, String snsProfile) {
        this.snsID = snsID;
        this.snsName = snsName;
        this.snsProfile = snsProfile;
    }
}
