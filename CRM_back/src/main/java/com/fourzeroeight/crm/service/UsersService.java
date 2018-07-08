package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Roles;
import com.fourzeroeight.crm.bean.Users;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
public interface UsersService {
    List<Users> getAll();

    void delete(int id);

    void add(Users logs);

    void update(Users logs);
}
