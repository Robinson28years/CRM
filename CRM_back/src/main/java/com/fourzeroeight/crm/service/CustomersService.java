package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Customers;
import com.fourzeroeight.crm.bean.Search;

import java.util.List;

public interface CustomersService {
    List<Customers> getAll();

    void delete(int id);

    void add(Customers temp);

    void update(Customers temp);

    Search getAllSelect(Search search);
}
