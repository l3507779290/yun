package com.example.yunusermanager.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "login_user")
@Data
public class UserPojo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "account")
    private Long account;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
}
