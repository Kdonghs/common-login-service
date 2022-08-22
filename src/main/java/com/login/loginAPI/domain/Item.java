package com.login.loginAPI.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemID;

    private String name;

    private Integer price;

    private String register;

    private Integer volume;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Item(String name, Integer price, String register) {
        this.name = name;
        this.price = price;
        this.register = register;
    }

    public Item(){}
}
