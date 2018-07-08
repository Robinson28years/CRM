package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Logs;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
public interface LogsService {
    List<Logs> getAll();

    void delete(int id);

    void add(Logs logs);

    void update(Logs logs);
}
