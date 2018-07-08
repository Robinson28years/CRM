package com.fourzeroeight.crm.controller;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.bean.Message;
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
    private BookinfoService bookinfoService;

    @RequestMapping("getAll")
    public Message getAll(){
        return new Message(1,null,bookinfoService.getAll());
    }

    @RequestMapping("delete")
    public Message delete(@RequestParam int id){
        bookinfoService.delete(id);
        return new Message(1,null,null);
    }

    @RequestMapping("add")
    public Message add(@RequestBody Bookinfo bookinfo){
        bookinfoService.add(bookinfo);
        return new Message(1,null,null);
    }

    @RequestMapping("update")
    public Message update(@RequestBody Bookinfo bookinfo){
        bookinfoService.update(bookinfo);
        return new Message(1,null,null);
    }

}
