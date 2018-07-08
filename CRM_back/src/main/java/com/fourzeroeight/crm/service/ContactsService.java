package com.fourzeroeight.crm.service;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.bean.Contacts;

import java.util.List;

public interface ContactsService {
    List<Contacts> getAll();

    void delete(int id);

    void add(Contacts temp);

    void update(Contacts temp);
}
