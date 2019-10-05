package com.neuedu.service;

import com.neuedu.entity.Admin;
import com.neuedu.entity.AdminOperationLog;
import com.neuedu.entity.User;

import java.sql.Timestamp;
import java.util.List;

public interface IAdminOperationLogService {
    List<AdminOperationLog> findAll();

    AdminOperationLog findByIdAndOperationTime(AdminOperationLog adminOperationLog);

    //    List<Admin> findById(int id);
//
//    Admin findByName(String aname);
//
    boolean add(Admin admin, User user, String operation);

    boolean rollback(int id,Timestamp operationTime);
//    boolean update(Admin admin);
//
    boolean del(AdminOperationLog adminOperationLog);
//
//    Admin logIn(String aname, String apwd);

}
