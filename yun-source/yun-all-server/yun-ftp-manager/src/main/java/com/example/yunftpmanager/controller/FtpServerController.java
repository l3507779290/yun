package com.example.yunftpmanager.controller;

import com.example.yunftpmanager.service.FtpServerService;
import com.example.yunusermanager.pojo.UserFilePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/ftp")
public class FtpServerController {

    @Autowired
    private FtpServerService ftpServerService;

    @RequestMapping("/all")
    public List<UserFilePojo> findAllUserList(){
        return  ftpServerService.findAllFileList();
            }

}
