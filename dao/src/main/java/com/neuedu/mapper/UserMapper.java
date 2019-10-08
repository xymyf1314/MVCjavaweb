package com.neuedu.mapper;

import com.neuedu.entity.User;

import java.util.List;

/**
 * @author fan
 */
public interface UserMapper {
    /**
     * 查找所有的User
     *
     * @return 返回查找的结果List<User>
     */
    List<User> findAll();

    /**
     * 通过id查找User
     *
     * @param id id
     * @return 返回查找的结果User
     */
    User findById(int id);

    /**
     * 通过用户名查找
     *
     * @param userName 用户名
     * @return 返回查找的结果User
     */
    User findByName(String userName);

    /**
     * 添加用户的方法
     *
     * @param user 待添加的User
     * @return 是否添加成功
     */
    boolean add(User user);

    /**
     * 更新修改用户的方法
     *
     * @param user 待修改的User
     * @return 是否添加成功
     */
    boolean update(User user);

    /**
     * 冻结用户
     *
     * @param user 待冻结的User
     * @return 是否冻结成功
     */
    boolean frost(User user);

    /**
     * 管理员回滚的方法
     *
     * @param user 需要回滚恢复的User
     * @return 是否回滚成功
     */
    boolean rollback(User user);

    /**
     * 回滚被删除用户所用的添加方法
     *
     * @param user 被删除的用户
     * @return 是否回滚成功
     */
    boolean addrollback(User user);

    /**
     * 删除用户
     *
     * @param id 待删除用户的id
     * @return 是否删除成功
     */
    boolean del(int id);
}
