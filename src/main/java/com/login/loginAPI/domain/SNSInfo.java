package com.login.loginAPI.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "`snsinfo`")
@NoArgsConstructor
public class SNSInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String snsID;

    @Enumerated(EnumType.STRING)
    private Social snsType;

    private String snsName;

    private String snsProfile;

    @CreatedDate
    private LocalDateTime snsConnectDate;

    @ManyToOne(optional = false)
    @JoinTable(name = "member_snsInfo", joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "memberID"))
    private Member memberId;

}
