package com.example.yunusermanager.util;


import java.util.Date;

public class idCrudCreateUtil {

    public static void main(String[] args) {
        System.out.println(getIdCard());
    }

    public static long getIdCard(){
        Date date=new Date();
        long time = date.getTime();
        return (time/2123);
    }
}
