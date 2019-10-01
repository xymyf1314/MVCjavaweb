package com.neuedu.mapper;


import com.neuedu.entity.AdminOperationLog;

import java.util.List;

public interface AdminOperationLogMapper {
    List<AdminOperationLog> findAll();

//    List<AdminOperationLog> findById(int id);
//
//    AdminLog findByName(String aName);
//
    boolean add(AdminOperationLog adminOperationLog);
//
//    boolean update(AdminOperationLog adminOperationLog);
//
//    boolean del(int id);
}
