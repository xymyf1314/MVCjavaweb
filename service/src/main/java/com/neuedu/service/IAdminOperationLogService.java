package com.neuedu.service;

import com.neuedu.entity.Admin;
import com.neuedu.entity.AdminOperationLog;
import com.neuedu.entity.User;

import java.util.List;

public interface IAdminOperationLogService {
    List<AdminOperationLog> findAll();

//    List<Admin> findById(int id);
//
//    Admin findByName(String aname);
//
    boolean add(Admin admin, User user,String operation);
//
//    boolean update(Admin admin);
//
//    boolean del(int id);
//
//    Admin logIn(String aname, String apwd);

}
