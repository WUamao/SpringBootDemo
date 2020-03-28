package com.amao.springboot.service;

import com.amao.springboot.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "atguigu.emps")
    public void receive(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive2(Message message){
        System.out.println("收到消息体："+message.getBody().getClass());
        System.out.println("收到消息头："+message.getMessageProperties());
    }
}
