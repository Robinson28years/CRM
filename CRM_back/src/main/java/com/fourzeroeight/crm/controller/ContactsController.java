package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Contacts;
import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.bean.Search;
import com.fourzeroeight.crm.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {

    @Autowired
    private ContactsService service;

    @RequestMapping("getAll")
    public Message getAll(){
        return new Message(1,null,service.getAll());
    }
    @RequestMapping("getAllSelect")
    public Message getAllSelect(@RequestBody Search search){
        return new Message(1,null,service.getAllSelect(search));
    }
    @RequestMapping("delete")
    public Message delete(@RequestParam int id){
        service.delete(id);
        return new Message(1,null,null);
    }

    @RequestMapping("add")
    public Message add(@RequestBody Contacts temp){
        service.add(temp);
        return new Message(1,null,null);
    }

    @RequestMapping("update")
    public Message update(@RequestBody Contacts temp){
        service.update(temp);
        return new Message(1,null,null);
    }

}
