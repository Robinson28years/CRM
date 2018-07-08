package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Files;
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

    @Override
    public List<Files> getAll() {
        return mapper.select();
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
