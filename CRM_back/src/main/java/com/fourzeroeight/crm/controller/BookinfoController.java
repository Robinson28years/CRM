package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.bean.Search;
import com.fourzeroeight.crm.service.BookinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookinfo")
public class BookinfoController {

    @Autowired
    private BookinfoService service;

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
    public Message add(@RequestBody Bookinfo bookinfo){
        service.add(bookinfo);
        return new Message(1,null,null);
    }

    @RequestMapping("update")
    public Message update(@RequestBody Bookinfo bookinfo){
        service.update(bookinfo);
        return new Message(1,null,null);
    }

}
