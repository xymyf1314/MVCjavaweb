package com.neuedu.mapper;


import com.neuedu.entity.Admin;

import java.util.List;

public interface AdminMapper {
    List<Admin> findAll();

    List<Admin> findById(int id);

    Admin findByName(String aName);

    boolean add(Admin admin);

    boolean update(Admin admin);

    boolean del(int id);
}
