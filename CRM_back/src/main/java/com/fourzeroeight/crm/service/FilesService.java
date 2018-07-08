package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Files;

import java.util.List;

/**
 * Created by 62450 on 2018/7/8.
 */
public interface FilesService {
    List<Files> getAll();

    void delete(int id);

    void add(Files files);

    void update(Files files);
}
