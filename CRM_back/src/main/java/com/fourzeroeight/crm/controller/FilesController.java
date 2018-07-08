package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Files;
import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 62450 on 2018/7/8.
 */
@RestController
@RequestMapping("/api/files")
public class FilesController {
    @Autowired
    private FilesService service;

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
    public Message add(@RequestBody Files files){
        service.add(files);
        return new Message(1,null,null);
    }

    @RequestMapping("update")
    public Message update(@RequestBody Files files){
        service.update(files);
        return new Message(1,null,null);
    }
}
