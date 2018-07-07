package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.service.BookinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookinfo")
public class BookinfoController {

    @Autowired
    private BookinfoService bookinfoService;

    @RequestMapping("getAll")
    public Message getAll(){
        return new Message(1,null,bookinfoService.getAll());
    }

}
