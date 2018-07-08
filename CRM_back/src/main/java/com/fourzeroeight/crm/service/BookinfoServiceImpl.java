package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.mapper.BookinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookinfoServiceImpl implements BookinfoService {

    @Autowired
    private BookinfoMapper bookinfoMapper;

    @Override
    public List<Bookinfo> getAll() {
        return bookinfoMapper.select();
    }

    @Override
    public void delete(int id) {
        bookinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Bookinfo bookinfo) {
        bookinfoMapper.insertSelective(bookinfo);
    }

    @Override
    public void update(Bookinfo bookinfo) {
        bookinfoMapper.updateByPrimaryKeySelective(bookinfo);
    }


}
