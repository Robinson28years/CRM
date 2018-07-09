package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Files;
import com.fourzeroeight.crm.bean.Search;
import com.fourzeroeight.crm.mapper.CustomersMapper;
import com.fourzeroeight.crm.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    private FilesMapper mapper;
    @Autowired
    private CustomersMapper customersMapper;
    @Override
    public List<Files> getAll() {
        List<Files> list = mapper.select();
        for (Files b : list) {
            b.setCust(customersMapper.selectByPrimaryKey(b.getCustid()));
        }
        return list;
    }

    @Override
    public Search getAllSelect(Search search) {
        List<Files> list = mapper.getListBySearch(search);
        for (Files b : list) {
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
    public void add(Files logs) {
        mapper.insertSelective(logs);
    }

    @Override
    public void update(Files logs) {
        mapper.updateByPrimaryKeySelective(logs);
    }
}
