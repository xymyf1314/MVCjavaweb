package com.neuedu.service;

import com.neuedu.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查找所有Product的方法
     *
     * @return 所有查找到的product的List集合
     */
    List<Product> findAll();

    /**
     * 删除方法
     *
     * @param id 待删除id
     * @return 是否删除成功
     */
    void delFromCategoryId(int id);


}
