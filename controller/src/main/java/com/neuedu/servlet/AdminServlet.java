package com.neuedu.servlet;

import com.neuedu.entity.Admin;
import com.neuedu.entity.AdminLog;
import com.neuedu.entity.AdminOperationLog;
import com.neuedu.entity.User;
import com.neuedu.mapper.AdminLogMapper;
import com.neuedu.mapper.AdminMapper;
import com.neuedu.mapper.AdminOperationLogMapper;
import com.neuedu.mapper.UserMapper;
import com.neuedu.service.serviceimpl.AdminLogServiceImpl;
import com.neuedu.service.serviceimpl.AdminOperationLogServiceImpl;
import com.neuedu.service.serviceimpl.AdminServiceImpl;
import com.neuedu.service.serviceimpl.UserServiceImpl;
import com.neuedu.util.MyBatisUtil;
import com.neuedu.util.ServletUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: servletTest03
 * @description: servlet
 * @author: LinLuo
 * @create: 2019-09-16 11:26
 **/
@WebServlet("*.admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决中文乱码
        ServletUtil.setCharacter(request, response);
        SqlSession session = MyBatisUtil.getSqlSession("mybatis-config.xml");
        HttpSession httpSession = request.getSession();
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        UserMapper uMapper = session.getMapper(UserMapper.class);
        AdminLogMapper Lmapper = session.getMapper(AdminLogMapper.class);
        AdminOperationLogMapper AOmapper = session.getMapper(AdminOperationLogMapper.class);
        // 调用服务层
        AdminServiceImpl adminService = new AdminServiceImpl(mapper);
        UserServiceImpl userService = new UserServiceImpl(uMapper);
        AdminLogServiceImpl adminLogService = new AdminLogServiceImpl(Lmapper);
        AdminOperationLogServiceImpl adminOperationLogService = new AdminOperationLogServiceImpl(AOmapper, uMapper);
        String url = request.getRequestURL().toString();
        String substring = url.substring(url.lastIndexOf("/"), url.lastIndexOf(".admin"));
        // 查找全部
        if ("/findAll".equals(substring)) {
            System.out.println("findAll");
            Admin admin = (Admin) httpSession.getAttribute("admin");
            System.out.println(admin.getJurisdiction());
            if (admin.getJurisdiction() != 0) {
                response.sendRedirect("error1.jsp");
                return;
            }
            List<Admin> admins = adminService.findAll();
            request.setAttribute("admins", admins);
            request.getRequestDispatcher("admin-list.jsp").forward(request, response);
            // 登陆
        } else if ("/frost".equals(substring)) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.findById(id);
            userService.frost(user);
            session.commit();
            // 登录日志
        } else if ("/loginLog".equals(substring)) {
            System.out.println("Administrator-login-log");

            Admin admin = (Admin) httpSession.getAttribute("admin");
            System.out.println(admin.getJurisdiction());
            if (admin.getJurisdiction() != 0) {
                response.sendRedirect("error1.jsp");
                return;
            }
            List<AdminLog> adminLogs = adminLogService.findAll();
            request.setAttribute("adminLogs", adminLogs);
            request.getRequestDispatcher("admin-login-log.jsp").forward(request, response);
            // 查看管理员操作日志
        } else if ("/adminOperationLog".equals(substring)) {
            Admin admin = (Admin) httpSession.getAttribute("admin");
            if (admin.getJurisdiction() != 0) {
                response.sendRedirect("error1.jsp");
                return;
            }
            System.out.println("Administrator-operation-log");
            List<AdminOperationLog> adminOperationLogs = adminOperationLogService.findAll();
            for (AdminOperationLog adminOperationLog : adminOperationLogs) {
                System.out.println(adminOperationLog);
            }
            request.setAttribute("adminOperationLogs", adminOperationLogs);
            request.getRequestDispatcher("admin-operation-log.jsp").forward(request, response);
            // 超级管理员的回滚操作
        } else if ("/rollback".equals(substring)) {
            System.out.println("rollback");
            int id = Integer.parseInt(request.getParameter("aid"));
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ts = Timestamp.valueOf(request.getParameter("operationTime"));
            boolean rollback = adminOperationLogService.rollback(id, ts);
            AdminOperationLog adminOperationLog = new AdminOperationLog();
            adminOperationLog.setId(id);
            adminOperationLog.setOperationTime(ts);
            adminOperationLogService.del(adminOperationLog);
            session.commit();
            // 添加用户
        } else if ("/add".equals(substring)) {
            System.out.println("add");
            Admin admin = new Admin();
            String aname = request.getParameter("aname");
            String apwd = request.getParameter("apwd");
            admin.setAName(aname);
            admin.setAPwd(apwd);
            adminService.add(admin);
            session.commit();
            response.sendRedirect("findAll.admin");
        } else if ("/load".equals(substring)) {
            System.out.println("load");
            int id = Integer.parseInt(request.getParameter("id"));
            List<Admin> list = adminService.findById(id);
            Admin admin = new Admin();
            admin.setId(list.get(0).getId());
            admin.setAName(list.get(0).getAName());
            admin.setAPwd(list.get(0).getAPwd());
            request.setAttribute("admin", admin);
            System.out.println(admin);
            request.getRequestDispatcher("admin-edit2.jsp").forward(request, response);
            // 修改用户信息
        } else if ("/update".equals(substring)) {
            System.out.println("update");
            Admin admin = new Admin();
            admin.setId(Integer.parseInt(request.getParameter("id")));
            admin.setAName(request.getParameter("aname"));
            admin.setAPwd(request.getParameter("apwd"));
            System.out.println(admin);
            boolean update = adminService.update(admin);
            System.out.println(update);
            session.commit();
            response.sendRedirect("findAll.admin");
            // 删除用户
        } else if ("/del".equals(substring)) {
            System.out.println("del");
            String id = request.getParameter("aid");
            System.out.println(id);
//            int id = Integer.parseInt(request.getParameter("id"));
            boolean del = adminService.del(Integer.parseInt(id));
            session.commit();
            System.out.println(del);
            response.sendRedirect("findAll.admin");
            // 登陆的方法
        } else if ("/login".equals(substring)) {
            System.out.println("login================");
            String checkcode = (String) request.getSession().getAttribute("checkcode");
            String aname = request.getParameter("aname");
            String apwd = request.getParameter("apwd");
            String yzm = request.getParameter("yzm");
            if (!(checkcode.equals(yzm))) {
                request.setAttribute("msg", "验证码错误");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            Admin admin = adminService.logIn(aname, apwd);
            if (admin != null) {
                httpSession.setAttribute("admin", admin);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                AdminLog adminLog = new AdminLog(admin.getId(), admin.getAName(), "登陆", admin.getJurisdiction());
                adminLogService.add(adminLog);
                session.commit();
                System.out.println("登陆成功");
            } else {
                request.setAttribute("msg", "用户名或密码错误");
                request.getRequestDispatcher("login.jsp").forward(request, response);
//                response.sendRedirect("/servletTest03_war/login.jsp");
            }
            // 退出的方法
        } else if ("/logout".equals(substring)) {
            int id = Integer.parseInt(request.getParameter("id"));
            List<Admin> admin = adminService.findById(id);
            AdminLog adminLog = new AdminLog(id, admin.get(0).getAName(), "退出", admin.get(0).getJurisdiction());
            adminLogService.add(adminLog);
            session.commit();
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            // 重置密码的方法
        } else if ("/reset".equals(substring)) {
            System.out.println("reset");
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.findById(id);
            user.setUserPassword("123456");
            System.out.println(user);
            boolean update = userService.update(user);
            session.commit();
            response.sendRedirect("userServlet?method=findAll");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}