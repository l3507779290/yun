package com.example.yunusermanager.server.impl;

import com.example.yunusermanager.dao.FileMapper;
import com.example.yunusermanager.dao.UserMapper;
import com.example.yunusermanager.pojo.UserFilePojo;
import com.example.yunusermanager.pojo.UserPojo;
import com.example.yunusermanager.server.UserServer;
import com.example.yunusermanager.util.IOUserFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional //开始事务支持
public class UserServerImpl implements UserServer {
    private static final Logger logger = LoggerFactory.getLogger(UserServer.class);


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileMapper fileMapper;

    private IOUserFileUtil ioUserFileUtil=new IOUserFileUtil();
    /**
     * FTP新增用户
     * @param userPojo
     * @return
     */
    @Override
    @Transactional
    public boolean registerUser(UserPojo userPojo) {
        logger.info(this.getClass().getCanonicalName()+"用户注册："+userPojo);
        //插入注册用户表
        int insert = userMapper.insert(userPojo);
        logger.info(this.getClass().getName()+"插入表：login_user 数据："+userPojo+" 返回结果："+insert);
        //新增用户返回结果
        boolean newUser=insert > 0 ? true:false;
        //增加用户文件夹返回结果
        boolean userFile = false;
        if (insert > 0){

            //IO创建用户文件夹
            UserFilePojo userFilePojo = ioUserFileUtil.IOSetFileName(userPojo.getAccount());
            logger.info(this.getClass().getName()+"创建用户存储文件目录："+userPojo.getAccount());
            //新增文件表信息
            int insert1 = fileMapper.insert(userFilePojo);
            logger.info(this.getClass().getName()+"插入表：user_file 数据"+userFilePojo+"返回结果："+insert1);
            userFile = insert1 >0 ? true:false;

        }
        return newUser == userFile == true ? true:false ;
    }

    /**
     * 登录FTP用户
     * @param account
     * @param password
     * @return
     */
    @Override
    public UserPojo loginUser(long account,String password) {
        UserPojo userPojo=new UserPojo();
        userPojo.setAccount(account);
        userPojo.setPassword(password);
        UserPojo result=userMapper.selectOne(userPojo);
        logger.info(this.getClass().getName()+"查询表：log_user 数据"+userPojo+"返回结果："+result);
        return result;
    }

    /**
     *删除用户
     * @param userPojo
     * @return
     */
    @Override
    @Transactional
    public boolean deleteUser(UserPojo userPojo) {
        //删除user表数据
        int deleteUser = userMapper.delete(userPojo);
        logger.info(this.getClass().getName()+"删除表：log_user 数据"+userPojo+"返回结果："+deleteUser);
        UserFilePojo userFilePojo=new UserFilePojo();
        userFilePojo.setAccount(userPojo.getAccount());
        //删除实体文件夹
        boolean deleteFileName = ioUserFileUtil.IODeleteFileName(userPojo.getAccount());
        logger.info(this.getClass().getName()+"删除用户文件目录："+userPojo.getAccount()+"  返回结果："+deleteFileName);
        //删除userFile表用户数据
        int deleteUserFile = fileMapper.delete(userFilePojo);
        boolean flag=false;
        if (deleteUser>0&& deleteUserFile>0 &&deleteFileName==true){
            flag=true;
        }
        logger.info(getClass().getCanonicalName()+"删除表：user_file 数据"+userFilePojo+"返回结果："+deleteUserFile);
        return flag;
    }


    /**
     * 条件构建
     * @param user
     * @return
     */
    private Example exeacuteExample(UserPojo user){
        Example example=new Example(UserPojo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",user.getId());
        criteria.andEqualTo("account", user.getAccount());
        criteria.andEqualTo("password",user.getPassword());
        criteria.andEqualTo("username",user.getUsername());
        return example;
    }
}
