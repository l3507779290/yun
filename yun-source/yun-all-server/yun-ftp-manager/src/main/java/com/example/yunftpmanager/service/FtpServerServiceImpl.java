package com.example.yunftpmanager.service;

import com.example.yunftpmanager.dao.FtpServerMapper;
import com.example.yunftpmanager.pojo.addFtpUser;
import com.example.yunusermanager.pojo.UserFilePojo;
import lombok.SneakyThrows;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FtpServerServiceImpl implements FtpServerService , ApplicationRunner {
    //已添加的FTP用户
    private List<String> isAddUser=new ArrayList<String>();
    @Autowired
    private FtpServerMapper ftpServerMapper;
    //实例化
    @Autowired
    private OpenFtpServer openFtpServer;
    private FtpServerFactory ftpServer=null;
    /**
     * 项目启动时运行
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ftpServer= openFtpServer.getFtpServer();
    }
    /**
     * 启动FTP服务
     * 动态添加FTP用户
     * 动态删除FTP用户
     * 持续检索服务器信息
     */
    @SneakyThrows
    @Scheduled(cron = "0/5 * * * * ?")
    public synchronized void DnyAddFTPServerUser(){
        List<addFtpUser> user = ftpServerMapper.findAllUserFileAddress();
        String[] account = ftpServer.getUserManager().getAllUserNames();
        //生成要添加的用户和要删除的用户，确保数据库文件与FTP用户数据同步
        if (user.size()!=account.length){
            for (int i = account.length; i <user.size() ; i++) {
                addFtpUser(user.get(i).getAccount(),user.get(i).getPassword(),user.get(i).getFilepath());
                System.out.println("新增ftp用户"+user.get(i).getAccount());
            }
        }
    }
    /**
     * 查询全部文件信息
     * @return
     */
    @Override
    public List<UserFilePojo> findAllFileList() {
        return ftpServerMapper.selectAll();
    }
    @SneakyThrows
    private void addFtpUser(long account , String password , String hostAddress) {
        String accounts = String.valueOf(account);
        BaseUser baseUser=new BaseUser();
        baseUser.setName(accounts);
        baseUser.setPassword(password);
        baseUser.setHomeDirectory(hostAddress);
        ftpServer.getUserManager().save(baseUser);
    }

}
