package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Message;
import com.fourzeroeight.crm.bean.Roles;
import com.fourzeroeight.crm.bean.Users;
import com.fourzeroeight.crm.mapper.RolesMapper;
import com.fourzeroeight.crm.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper mapper;
    @Autowired
    private RolesMapper rolesMapper;

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

    @Override
    @Transactional
    public Message login(Users userInfo) {
        if(userInfo.getUsername() != null && !userInfo.getUsername().equals("")){
            if(userInfo.getPassword() != null && !userInfo.getPassword().equals("")){
                Users users = mapper.selectByUsername(userInfo.getUsername());
                if(users != null){
                    if(userInfo.getPassword().equals(users.getPassword())){
                        if(users.getStatus() == 0){
                            users.setRoles(rolesMapper.selectByPrimaryKey(users.getRoleid()));
                            users.setPassword(null);
                            return new Message(1,"登陆成功",users);
                        } else {
                            throw new RuntimeException("用户已被冻结");
                        }
                    } else {
                        throw new RuntimeException("用户名或者密码错误");
                    }
                } else {
                    throw new RuntimeException("用户名不存在");
                }
            } else {
                throw new RuntimeException("密码不能为空");
            }
        } else{
            throw new RuntimeException("用户名不能为空");
        }
    }
}
