package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Logs;
import com.fourzeroeight.crm.bean.Search;
import com.fourzeroeight.crm.mapper.CustomersMapper;
import com.fourzeroeight.crm.mapper.LogsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    private LogsMapper mapper;
    @Autowired
    private CustomersMapper customersMapper;
    @Override
    public List<Logs> getAll() {
        List<Logs> list = mapper.select();
        for (Logs b : list) {
            b.setCust(customersMapper.selectByPrimaryKey(b.getCustid()));
        }
        return list;
    }
    @Override
    public Search getAllSelect(Search search) {
        List<Logs> list = mapper.getListBySearch(search);
        for (Logs b : list) {
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
    public void add(Logs logs) {
        mapper.insertSelective(logs);
    }

    @Override
    public void update(Logs logs) {
        mapper.updateByPrimaryKeySelective(logs);
    }
}
