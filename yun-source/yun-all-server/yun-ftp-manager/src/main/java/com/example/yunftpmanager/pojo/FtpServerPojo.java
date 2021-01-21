package com.example.yunftpmanager.pojo;

import lombok.Data;
import org.apache.ftpserver.FtpServer;

@Data
public class FtpServerPojo {
    private long Account;//FTP账号
    private String password;//FTP密码
    private String FilePath;//FTP当前用户默认目录
    private String HostAddress;//返回FTP访问地址
    private FtpServer service;//返回的ftp服务，可启动ftp
}
