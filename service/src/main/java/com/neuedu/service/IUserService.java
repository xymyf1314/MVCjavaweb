package com.neuedu.service;


import com.neuedu.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User findById(int id);

    User findByName(String userName);

    boolean add(User user);

    boolean update(User user);

    boolean rollback(User user);

    boolean frost(User user);

    boolean del(int id);

    public User logIn(String userName, String userPassword);

}
