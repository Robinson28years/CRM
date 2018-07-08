package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Customers;
import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Customers")
public class CustomersController {

    @Autowired
    private CustomersService service;

    @RequestMapping("getAll")
    public Message getAll(){
        return new Message(1,null,service.getAll());
    }

    @RequestMapping("delete")
    public Message delete(@RequestParam int id){
        service.delete(id);
        return new Message(1,null,null);
    }

    @RequestMapping("add")
    public Message add(@RequestBody Customers temp){
        service.add(temp);
        return new Message(1,null,null);
    }

    @RequestMapping("update")
    public Message update(@RequestBody Customers temp){
        service.update(temp);
        return new Message(1,null,null);
    }

}
