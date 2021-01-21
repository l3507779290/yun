package com.example.yunftpmanager.service;

import lombok.SneakyThrows;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenFtpServer {
    private static final int porn=2222 ;
    private  FtpServerFactory serverFactory=new FtpServerFactory();
    /**
     * 启动FTP服务
     * @return
     */
    @SneakyThrows
    public FtpServerFactory getFtpServer(){
                ListenerFactory factory = new ListenerFactory();
            factory.setPort(porn);
            serverFactory.addListener("default", factory.createListener());
             List<Authority> authorities = new ArrayList<Authority>();
            authorities.add(new WritePermission());
        FtpServer server = serverFactory.createServer();
        server.start();
        return serverFactory;
   }
}
