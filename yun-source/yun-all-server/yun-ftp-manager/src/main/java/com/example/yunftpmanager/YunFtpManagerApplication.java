package com.example.yunftpmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.yunftpmanager.dao")
@EnableScheduling
public class YunFtpManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunFtpManagerApplication.class, args);
	}

}
