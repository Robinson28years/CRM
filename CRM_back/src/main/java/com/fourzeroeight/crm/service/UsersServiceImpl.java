package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Roles;
import com.fourzeroeight.crm.bean.Users;
import com.fourzeroeight.crm.mapper.RolesMapper;
import com.fourzeroeight.crm.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper mapper;

    @Override
    public List<Users> getAll() {
        return mapper.select();
    }

    @Override
    public void delete(int id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Users logs) {
        mapper.insertSelective(logs);
    }

    @Override
    public void update(Users logs) {
        mapper.updateByPrimaryKeySelective(logs);
    }
}
