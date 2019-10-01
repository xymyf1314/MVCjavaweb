package com.neuedu.service;

import com.neuedu.entity.AdminLog;

import java.util.List;

public interface IAdminLogService {
    List<AdminLog> findAll();

//    List<Admin> findById(int id);
//
//    Admin findByName(String aname);
//
    boolean add(AdminLog adminLog);
//
//    boolean update(Admin admin);
//
//    boolean del(int id);
//
//    Admin logIn(String aname, String apwd);

}
