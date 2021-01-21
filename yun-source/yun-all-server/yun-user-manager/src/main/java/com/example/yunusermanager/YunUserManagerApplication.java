package com.example.yunusermanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.yunusermanager.dao")
@EnableTransactionManagement //添加事务处理
public class YunUserManagerApplication {
	/**
	 * 初始化日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(YunUserManagerApplication.class);

	/**
	 * 启动程序
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(YunUserManagerApplication.class, args);
		logger.info(YunUserManagerApplication.class.getName()+"启动程序！！！");
	}

}
