package com.example.yunftpmanager.pojo;

import java.io.Serializable;

public class addFtpUser implements Serializable {
    private Long account;
    private String password;
    private String filepath;

    public addFtpUser(){};

    public addFtpUser(Long account, String password, String filepath) {
        this.account = account;
        this.password = password;
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "addFtpUser{" +
                "account=" + account +
                ", password='" + password + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
