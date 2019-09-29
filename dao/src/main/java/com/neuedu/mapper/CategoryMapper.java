package com.neuedu.mapper;


import com.neuedu.entity.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> findAll();

    boolean insertRoot(Category category);

    boolean insertChild(Category category);

    List<Category> findToTree();
    List<Category> findById(int id);
//
//    Admin findByName(String aname);
//
//    boolean add(Admin admin);
//
    boolean update(Category category);
//
//    boolean del(int id);
}
