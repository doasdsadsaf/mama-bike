package com.coder520.mamabike.activeMQ.service;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ActiveMsgListener {

    @JmsListener(destination = "q2")
    public void rctiveMsg(String message){
        System.out.println("------监听到activemq的数据"+message);
    }
}
