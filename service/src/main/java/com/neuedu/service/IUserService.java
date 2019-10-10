package com.neuedu.service;


import com.neuedu.entity.User;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.service.serviceimpl.CategoryServiceImpl;
import com.neuedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

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

    class CategoryTest {
        public static void main(String[] args) {
            SqlSession sqlSession = MyBatisUtil.getSqlSession("mubatis-config.xml");
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryMapper, productMapper);
            categoryService.del(1);
            sqlSession.commit();
        }
    }
}
