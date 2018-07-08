package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Logs;
import com.fourzeroeight.crm.bean.Roles;
import com.fourzeroeight.crm.mapper.LogsMapper;
import com.fourzeroeight.crm.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesMapper mapper;

    @Override
    public List<Roles> getAll() {
        return mapper.select();
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Roles logs) {
        mapper.insertSelective(logs);
    }

    @Override
    public void update(Roles logs) {
        mapper.updateByPrimaryKeySelective(logs);
    }
}
