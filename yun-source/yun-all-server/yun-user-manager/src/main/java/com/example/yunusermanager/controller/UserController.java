package com.example.yunusermanager.controller;


import com.example.yunusermanager.pojo.UserPojo;
import com.example.yunusermanager.server.UserServer;
import com.example.yunusermanager.util.ResultUtil;
import com.example.yunusermanager.util.idCrudCreateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @RequestMapping("/index")
    public String index(){
        //System.out.println("index");
        return "index";
    }




    /**
     * 注册FTP用户
     * @param userPojo
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView registerFtpUser(UserPojo userPojo, ModelAndView mv, HttpServletRequest request, Model model, String password){
        if (userPojo.getUsername()!=null&&userPojo.getPassword()!=null){
            long idCrud= idCrudCreateUtil.getIdCard();
            userPojo.setAccount(idCrud);
            if (userServer.registerUser(userPojo)){
                request.setAttribute("message", "您的账号为："+idCrud);
                System.out.println(idCrud);
                mv.setViewName("/regsuccess");
            }else{
                request.setAttribute("message",ResultUtil.defeated);
            }
        }else{
            mv.setViewName("/register");
        }
        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView loginFtpUser( UserPojo userPojo,ModelAndView mv, HttpServletRequest request, Model model) {
        System.out.println(userPojo.getUsername());
        if (userPojo.getAccount()!=null&&userPojo.getPassword()!=null){
            UserPojo user = userServer.loginUser(userPojo.getAccount(),userPojo.getPassword());
            if (user==null){
                mv.setViewName("/login");
                    request.setAttribute("message","登陆失败，用户名或密码不正确！！！");
            }else
            if (user.getUsername()!=null){
                System.out.println("登陆成功！"+user.getUsername());
                HttpSession session = request.getSession();
                session.setAttribute("username",user.getUsername());
                request.setAttribute("message",user.getUsername());
                mv.setViewName("/index");
            }else{

            }
        }else {mv.setViewName("/login");}
        return mv;
    }

    @PostMapping(value = "/delete")
    public String deleteFtpUser(@RequestBody UserPojo userPojo){
        boolean deleteUser = userServer.deleteUser(userPojo);
        return deleteUser == true ? "删除成功！":"删除失败";
    }

}
