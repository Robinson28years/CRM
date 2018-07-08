package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.mapper.BookinfoMapper;
import com.fourzeroeight.crm.mapper.CustomersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class BookinfoServiceImpl implements BookinfoService {

    @Autowired
    private BookinfoMapper bookinfoMapper;

    @Autowired
    private CustomersMapper customersMapper;

    @Override
    @Transactional
    public List<Bookinfo> getAll() {
        List<Bookinfo> list = bookinfoMapper.select();
        for (Bookinfo b : list) {
            b.setCust(customersMapper.selectByPrimaryKey(b.getCustid()));
        }
        return list;
    }

    @Override
    public void delete(int id) {
        bookinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Bookinfo bookinfo) {
        bookinfoMapper.insertSelective(bookinfo);
    }

    @Override
    public void update(Bookinfo bookinfo) {
        bookinfoMapper.updateByPrimaryKeySelective(bookinfo);
    }


}
