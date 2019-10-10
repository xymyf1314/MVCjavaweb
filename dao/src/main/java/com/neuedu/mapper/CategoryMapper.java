package com.neuedu.mapper;


import com.neuedu.entity.Category;

import java.util.List;

/**
 * @author fan
 */
public interface CategoryMapper {
    /**
     * 查找所有的category
     *
     * @return 返回查找的结果List<Category>
     */
    List<Category> findAll();

    /**
     * 插入根节点
     *
     * @param category category类
     * @return 是否成功插入
     */
    boolean insertRoot(Category category);

    /**
     * 插入子节点
     *
     * @param category category类
     * @return 是否成功插入
     */
    boolean insertChild(Category category);

    /**
     * 以树结构查找
     *
     * @return 返回查找的结果List<Category>
     */
    List<Category> findToTree();

    /**
     * 通过id查找
     *
     * @param id id
     * @return 返回查找的结果List<Category>
     */
    List<Category> findById(int id);

    /**
     *
     * @param id
     * @return
     */
    List<Category> selectCategoryChildrenByPid(int id);
//
//    boolean add(Admin admin);

    /**
     * 更新修改category数据
     *
     * @param category 待修改的Category
     * @return 是否成功更新
     */
    boolean update(Category category);

    /**
     * 删除category的方法
     *
     * @param id 待删除id
     * @return 是否删除成功
     */
    boolean del(int id);
}
