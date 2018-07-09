package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Contacts;
import com.fourzeroeight.crm.bean.Search;
import com.fourzeroeight.crm.mapper.ContactsMapper;
import com.fourzeroeight.crm.mapper.CustomersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsMapper mapper;
    @Autowired
    private CustomersMapper customersMapper;

    @Override
    public List<Contacts> getAll() {
        List<Contacts> list = mapper.select();
        for (Contacts c:list) {
            c.setCust(customersMapper.selectByPrimaryKey(c.getCustid()));

        }
        return list;
    }

    @Override
    public Search getAllSelect(Search search) {
        List<Contacts> list = mapper.getListBySearch(search);
        for (Contacts b : list) {
            b.setCust(customersMapper.selectByPrimaryKey(b.getCustid()));
        }
        search.setObject(list);
        search.setTotal(mapper.getCountBySearch(search));
        return search;
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
