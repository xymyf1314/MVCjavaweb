package com.neuedu.service.serviceimpl;

import com.neuedu.entity.AdminLog;
import com.neuedu.mapper.AdminLogMapper;
import com.neuedu.service.IAdminLogService;

import java.util.List;

/**
 * @program: servletTest03
 * @description:
 * @author: LinLuo
 * @create: 2019-09-16 16:05
 **/
public class AdminLogServiceImpl implements IAdminLogService {
    AdminLogMapper adminLogMapper;

    public AdminLogServiceImpl(AdminLogMapper adminLogMapper) {
        this.adminLogMapper = adminLogMapper;
    }

    @Override
    public List<AdminLog> findAll() {
        System.out.println("查找事务");
        return adminLogMapper.findAll();
    }

    @Override
    public boolean add(AdminLog adminLog) {
        System.out.println("添加日志");
        return adminLogMapper.add(adminLog);
    }
}