package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Logs;
import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.bean.Roles;
import com.fourzeroeight.crm.service.LogsService;
import com.fourzeroeight.crm.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 62450 on 2018/7/8.
 */
@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RolesService service;

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
    public Message add(@RequestBody Roles logs){
        service.add(logs);
        return new Message(1,null,null);
    }

    @RequestMapping("update")
    public Message update(@RequestBody Roles logs){
        service.update(logs);
        return new Message(1,null,null);
    }
}
