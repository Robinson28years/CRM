package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.bean.Search;

import java.util.List;

public interface BookinfoService {
    List<Bookinfo> getAll();

    void delete(int id);

    void add(Bookinfo bookinfo);

    void update(Bookinfo bookinfo);

    Search getAllSelect(Search search);
}
