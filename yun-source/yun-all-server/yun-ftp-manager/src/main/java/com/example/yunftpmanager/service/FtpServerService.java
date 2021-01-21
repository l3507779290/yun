package com.example.yunftpmanager.service;

import com.example.yunusermanager.pojo.UserFilePojo;

import java.util.List;



public interface FtpServerService {

    /**
     * 查询全部文件信息
     * @return
     */
    public List<UserFilePojo> findAllFileList();

    /**
     * 动态处理
     */
    public void DnyAddFTPServerUser();
}
