package com.example.yunusermanager.pojo;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user_file")
@Data
public class UserFilePojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "filepath")
    private String filpath;
    @Column(name = "md5")
    private String md5;
    @Column(name = "account")
    private Long account;

}
