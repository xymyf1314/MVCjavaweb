package com.neuedu.mapper;


import com.neuedu.entity.AdminLog;

import java.util.List;

public interface AdminLogMapper {
    List<AdminLog> findAll();

    List<AdminLog> findById(int id);

    AdminLog findByName(String aName);

    boolean add(AdminLog adminLog);

    boolean update(AdminLog adminLog);

    boolean del(int id);
}
