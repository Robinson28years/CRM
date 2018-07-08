package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Customers;
import com.fourzeroeight.crm.mapper.CustomersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    private CustomersMapper mapper;

    @Override
    public List<Customers> getAll() {
        return mapper.select();
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Customers bookinfo) {
        mapper.insertSelective(bookinfo);
    }

    @Override
    public void update(Customers bookinfo) {
        mapper.updateByPrimaryKeySelective(bookinfo);
    }


}
