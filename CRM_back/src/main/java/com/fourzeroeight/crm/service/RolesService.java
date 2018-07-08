package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Logs;
import com.fourzeroeight.crm.bean.Roles;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
public interface RolesService {
    List<Roles> getAll();

    void delete(int id);

    void add(Roles logs);

    void update(Roles logs);
}
