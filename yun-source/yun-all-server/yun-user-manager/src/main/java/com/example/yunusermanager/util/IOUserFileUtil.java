package com.example.yunusermanager.util;


import com.example.yunusermanager.pojo.UserFilePojo;

import java.io.File;

public class IOUserFileUtil {
    //set Default File Path
    private PropertiesUtil propertiesUtil=new PropertiesUtil();
    private final String DefaultUserFilePath=propertiesUtil.getPropertiesKeyValue("IoCfg.properties","user.ftp.save.file.path");


    /**
     * 根据ID开通新文件夹
     * @param account
     * @return
     */
    public UserFilePojo IOSetFileName(long account){
        String path=DefaultUserFilePath+account;
        File file=new File(path);
        file.mkdir();
        UserFilePojo userFilePojo=new UserFilePojo();
        userFilePojo.setFilpath(path);
        userFilePojo.setAccount(account);
        return userFilePojo;
    }

    /**
     * 删除文件夹
     * @param account
     * @return
     */
    public boolean IODeleteFileName(long account){
        String path=DefaultUserFilePath+account;
        File file=new File(path);
        return file.delete();
    }


}
