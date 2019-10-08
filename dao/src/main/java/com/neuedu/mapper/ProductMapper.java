package com.neuedu.mapper;

import com.neuedu.entity.Product;

import java.util.List;

/**
 * @program: parentshop
 * @description: 商品的Mapper接口
 * @author: Linluo
 * @create: 2019-10-08 18:31
 **/


public interface ProductMapper {
    /**
     * 查找所有Product的方法
     *
     * @return 所有查找到的product的List集合
     */
    List<Product> findAll();
}
