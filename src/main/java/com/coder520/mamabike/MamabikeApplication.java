package com.coder520.mamabike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 这个类很关键 spring会扫描这个类地址下面的xml service controller ......
 */
@SpringBootApplication
// 开启事务
@EnableTransactionManagement
// 启动消息队列
@EnableJms
public class MamabikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MamabikeApplication.class);
        System.out.println("=======MamabikeApplication启动成功===========");
    }
}
