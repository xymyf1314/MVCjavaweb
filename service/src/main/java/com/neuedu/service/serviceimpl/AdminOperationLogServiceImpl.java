package com.neuedu.service.serviceimpl;

import com.neuedu.entity.Admin;
import com.neuedu.entity.AdminOperationLog;
import com.neuedu.entity.User;
import com.neuedu.mapper.AdminOperationLogMapper;
import com.neuedu.mapper.UserMapper;
import com.neuedu.service.IAdminOperationLogService;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: servletTest03
 * @description:
 * @author: LinLuo
 * @create: 2019-09-16 16:05
 **/
public class AdminOperationLogServiceImpl implements IAdminOperationLogService {
    AdminOperationLogMapper adminOperationLogMapper;
    UserMapper userMapper;


    public AdminOperationLogServiceImpl(AdminOperationLogMapper adminOperationLogMapper, UserMapper userMapper) {
        this.adminOperationLogMapper = adminOperationLogMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<AdminOperationLog> findAll() {
        System.out.println("查找事务");
        return adminOperationLogMapper.findAll();
    }

    @Override
    public AdminOperationLog findByIdAndOperationTime(AdminOperationLog adminOperationLog) {
        return adminOperationLogMapper.findByIdAndOperationTime(adminOperationLog);
    }

    @Override
    public boolean add(Admin admin, User user, String operation) {
        System.out.println("添加管理员操作日志");
        AdminOperationLog adminOperationLog = new AdminOperationLog(admin.getId(), admin.getAName(), operation, user.getId(), user.getUserName(), user.getUserPassword(),user.getUserGrade(),user.getUserPhone(), user.getUserAddress(), user.getUserRegisterDate(),user.getDisable());
        System.out.println(adminOperationLog);
        return adminOperationLogMapper.add(adminOperationLog);
    }

    @Override
    public boolean rollback(int id, Timestamp operationTime) {
        System.out.println("进行回滚业务操作");
        System.out.println(id);
        System.out.println(operationTime);
        AdminOperationLog adminOperationLog2 = new AdminOperationLog();
        adminOperationLog2.setId(id);
        adminOperationLog2.setOperationTime(operationTime);
        AdminOperationLog adminOperationLog = adminOperationLogMapper.findByIdAndOperationTime(adminOperationLog2);
        System.out.println(adminOperationLog);
        String operation = adminOperationLog.getOperation();
        System.out.println(operation);
        boolean flag = false;
        if ("修改".equals(operation)) {
            User user = new User(adminOperationLog.getUid(), adminOperationLog.getUserName(), adminOperationLog.getUserPassword(),adminOperationLog.getUserGrade(), adminOperationLog.getUserPhone(), adminOperationLog.getUserAddress(), adminOperationLog.getOperationTime(),adminOperationLog.getUserDisable());
            System.out.println(user);
            UserServiceImpl userService = new UserServiceImpl(userMapper);
            userService.
            userMapper.rollback(user);
        } else if ("增加".equals(operation)) {
            flag = userMapper.del(adminOperationLog.getUid());
        } else if ("删除".equals(operation)) {
            flag = userMapper.add(new User(adminOperationLog.getUid(), adminOperationLog.getUserName(), adminOperationLog.getUserPassword(),adminOperationLog.getUserGrade(), adminOperationLog.getUserPhone(), adminOperationLog.getUserAddress(), adminOperationLog.getOperationTime(),adminOperationLog.getUserDisable()));
        }
        return flag;
    }

}