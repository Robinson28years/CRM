package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Contacts;
import com.fourzeroeight.crm.bean.Customers;

import java.util.List;

public interface CustomersService {
    List<Customers> getAll();

    void delete(int id);

    void add(Customers temp);

    void update(Customers temp);
}
