package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.bean.Search;
import com.fourzeroeight.crm.mapper.BookinfoMapper;
import com.fourzeroeight.crm.mapper.CustomersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class BookinfoServiceImpl implements BookinfoService {

    @Autowired
    private BookinfoMapper mapper;

    @Autowired
    private CustomersMapper customersMapper;

    @Override
    @Transactional
    public List<Bookinfo> getAll() {
        List<Bookinfo> list = mapper.select();
        for (Bookinfo b : list) {
            b.setCust(customersMapper.selectByPrimaryKey(b.getCustid()));
        }
        return list;
    }
    @Override
    public Search getAllSelect(Search search) {
        List<Bookinfo> list = mapper.getListBySearch(search);
        for (Bookinfo b : list) {
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
    public void add(Bookinfo bookinfo) {
        mapper.insertSelective(bookinfo);
    }

    @Override
    public void update(Bookinfo bookinfo) {
        mapper.updateByPrimaryKeySelective(bookinfo);
    }


}
