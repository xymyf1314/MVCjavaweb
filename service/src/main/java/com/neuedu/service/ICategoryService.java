package com.neuedu.service;

import com.neuedu.entity.Category;

import java.util.List;

/**
 * @author fan
 */
public interface ICategoryService {
    /**
     * 查找所有的category
     *
     * @return category的List集合
     */
    List<Category> findAll();
    List<Category> selectCategoryChildrenByPid(int id);

    /**
     * 插入根节点
     *
     * @param categoryName        类别名
     * @param categoryDescription 类别描述
     * @return 是否成功
     */
    boolean insertRoot(String categoryName, String categoryDescription);

    /**
     * 插入子节点
     *
     * @param categoryName        类别名
     * @param categoryDescription 类别描述
     * @param categoryParentId    父类别id
     * @return 是否成功
     */
    boolean insertChild(String categoryName, String categoryDescription, int categoryParentId);

    /**
     * 以树形查找所有category
     *
     * @return category的List集合
     */
    List<Category> findToTree();

    /**
     * 通过id查找category
     *
     * @param id id
     * @return category的List集合
     */
    List<Category> findById(int id);

    /**
     * 更新category信息
     *
     * @param category Category对象
     * @return 是否成功
     */
    boolean update(Category category);

    /**
     * 删除方法
     *
     * @param id 待删除的id
     * @return 是否成功
     */
    void del(int id);


}
