package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.bean.Users;
import com.fourzeroeight.crm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/public")
public class LoginController {

    @Autowired
    private UsersService service;

    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public Message login(@RequestBody Users userInfo, HttpSession httpSession) {
        Message message = service.login(userInfo);
        if(message.getCode() == 1){
            httpSession.setAttribute("user",message.getRes());
        }
        return message;
    }

    @RequestMapping(value = "/logout" ,method = RequestMethod.GET)
    public Message logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return new Message(1,"注销成功!",null);
    }
}
