package com.example.yunftpmanager.dao;

import com.example.yunftpmanager.pojo.addFtpUser;
import com.example.yunusermanager.pojo.UserFilePojo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FtpServerMapper extends Mapper<UserFilePojo> {
    List<addFtpUser> findAllUserFileAddress();
}
