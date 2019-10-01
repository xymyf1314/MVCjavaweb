package com.neuedu.service.serviceimpl;

import com.neuedu.entity.Admin;
import com.neuedu.entity.AdminOperationLog;
import com.neuedu.entity.User;
import com.neuedu.mapper.AdminOperationLogMapper;
import com.neuedu.service.IAdminOperationLogService;

import java.util.List;

/**
 * @program: servletTest03
 * @description:
 * @author: LinLuo
 * @create: 2019-09-16 16:05
 **/
public class AdminOperationLogServiceImpl implements IAdminOperationLogService {
    AdminOperationLogMapper adminOperationLogMapper;

    public AdminOperationLogServiceImpl(AdminOperationLogMapper adminOperationLogMapper) {
        this.adminOperationLogMapper = adminOperationLogMapper;
    }

    @Override
    public List<AdminOperationLog> findAll() {
        System.out.println("查找事务");
        return adminOperationLogMapper.findAll();
    }

    @Override
    public boolean add(Admin admin, User user,String operation) {
        System.out.println("添加管理员操作日志");
        AdminOperationLog adminOperationLog = new AdminOperationLog(admin.getId(), admin.getAName(), operation, user.getId(), user.getUserName(), user.getUserPassword(), user.getUserPhone(), user.getUserAddress(), user.getUserRegisterDate());
        System.out.println(adminOperationLog);
        return adminOperationLogMapper.add(adminOperationLog);
    }
}