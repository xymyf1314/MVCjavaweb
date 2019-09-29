package com.neuedu.service;

import com.neuedu.entity.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    boolean insertRoot(String categoryName, String categoryDescription);

    boolean insertChild(String categoryName, String categoryDescription, int categoryParentId);

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
//
//    Admin logIn(String aname, String apwd);

}
