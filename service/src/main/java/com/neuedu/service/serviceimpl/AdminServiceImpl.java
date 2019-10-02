package com.neuedu.service.serviceimpl;

import com.neuedu.entity.Admin;
import com.neuedu.mapper.AdminMapper;
import com.neuedu.service.IAdminService;
import com.neuedu.util.ServletUtil;

import java.util.List;

/**
 * @program: servletTest03
 * @description:
 * @author: LinLuo
 * @create: 2019-09-16 16:05
 **/
public class AdminServiceImpl implements IAdminService {
    AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public List<Admin> findAll() {
        System.out.println("查找事务");
        return adminMapper.findAll();
    }

    @Override
    public List<Admin> findById(int id) {
        System.out.println("通过id查找");
        return adminMapper.findById(id);
    }

    @Override
    public Admin findByName(String aName) {
        System.out.println("通过name查找");
        return adminMapper.findByName(aName);
    }

    @Override
    public boolean add(Admin admin) {
        System.out.println("添加的事务");
        Admin result = this.findByName(admin.getAName());
        boolean add = false;
        if (result == null) {
            add = adminMapper.add(admin);
        }
        return add;
    }

    @Override
    public boolean update(Admin admin) {
        System.out.println("修改的事务");
        boolean add = adminMapper.update(admin);
        return add;
    }

    @Override
    public boolean del(int id) {
        System.out.println("删除的事务");
        boolean del = adminMapper.del(id);
        return del;
    }

    @Override
    public Admin logIn(String aname, String apwd) {
        System.out.println("服务层登陆事务");
        Admin admin = this.findByName(aname);
        System.out.println(admin);
        if (admin != null) {
            if (admin.getAPwd().equals(ServletUtil.md5(apwd))) {
                return admin;
            }
        }
        return null;
    }

}