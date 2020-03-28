package com.amao.user.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.amao.ticker.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Reference
    TicketService ticketService;

    public String hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票了："+ticket);
        return "买到票了："+ticket;
    }


}
