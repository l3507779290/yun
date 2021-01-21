package com.example.yunusermanager.server;


import com.example.yunusermanager.pojo.UserPojo;

public interface UserServer {
    /**
     * 注册Ftp用户
     * @param userPojo
     * @return
     */
    boolean registerUser(UserPojo userPojo);

    /**
     * 登录FTP用户
     * @param
     * @return
     */
    UserPojo loginUser(long account, String password);

    /**
     * 删除FTP用户
     * @param userPojo
     * @return
     */
    boolean deleteUser(UserPojo userPojo);


}
