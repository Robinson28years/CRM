package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Contacts;
import com.fourzeroeight.crm.mapper.ContactsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsMapper mapper;

    @Override
    public List<Contacts> getAll() {
        return mapper.select();
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Contacts bookinfo) {
        mapper.insertSelective(bookinfo);
    }

    @Override
    public void update(Contacts bookinfo) {
        mapper.updateByPrimaryKeySelective(bookinfo);
    }


}
