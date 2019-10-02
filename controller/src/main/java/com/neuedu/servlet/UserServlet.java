package com.neuedu.servlet;

import com.neuedu.entity.Admin;
import com.neuedu.entity.User;
import com.neuedu.mapper.AdminMapper;
import com.neuedu.mapper.AdminOperationLogMapper;
import com.neuedu.mapper.UserMapper;
import com.neuedu.service.serviceimpl.AdminOperationLogServiceImpl;
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
import java.util.List;

/**
 * @program: servletTest03
 * @description: servlet
 * @author: LinLuo
 * @create: 2019-09-16 11:26
 **/
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决中文乱码
        ServletUtil.setCharacter(request, response);
        SqlSession session = MyBatisUtil.getSqlSession("mybatis-config.xml");
        HttpSession httpSession = request.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        AdminOperationLogMapper adminOperationLogMapper = session.getMapper(AdminOperationLogMapper.class);
        // 调用服务层
        UserServiceImpl userService = new UserServiceImpl(mapper);
        AdminOperationLogServiceImpl adminOperationLogService = new AdminOperationLogServiceImpl(adminOperationLogMapper,mapper);
        String method = request.getParameter("method");
        if ("findAll".equals(method)) {
            System.out.println("findAll");
            List<User> users = userService.findAll();
            request.setAttribute("users", users);
            request.getRequestDispatcher("user-list.jsp").forward(request, response);

        } else if ("add".equals(method)) {
            System.out.println("add");
            User user = new User(request.getParameter("username"), request.getParameter("password"), request.getParameter("phone"), request.getParameter("addr"));
            Admin admin = (Admin) httpSession.getAttribute("admin");
            userService.add(user);
            User user1 = userService.findByName(user.getUserName());
            adminOperationLogService.add(admin, user1, "增加");
            session.commit();
            response.sendRedirect("userServlet?method=findAll");
        } else if ("load".equals(method)) {
            System.out.println("load");
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.findById(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("user-edit.jsp").forward(request, response);
        } else if ("update".equals(method)) {
            System.out.println("update");
            User user = new User();
            int id = Integer.parseInt(request.getParameter("id"));
            user.setId(id);
            user.setUserName(request.getParameter("username"));
            user.setUserPassword(request.getParameter("password"));
            user.setUserPhone(request.getParameter("phone"));
            user.setUserAddress(request.getParameter("addr"));
            User user1 = userService.findById(id);
            Admin admin = (Admin) httpSession.getAttribute("admin");
            boolean add = adminOperationLogService.add(admin, user1, "修改");
            System.out.println("===================");
            boolean update = userService.update(user);
            session.commit();
            response.sendRedirect("userServlet?method=findAll");
        } else if ("del".equals(method)) {
            System.out.println("del");
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.findById(id);
            Admin admin = (Admin) httpSession.getAttribute("admin");
            adminOperationLogService.add(admin, user, "删除");
            boolean del = userService.del(id);
            session.commit();
            response.sendRedirect("userServlet?method=findAll");
        }
//            // 登陆的方法
//        } else if ("/login".equals(substring)) {
//            System.out.println("login================");
//            String checkcode = (String) request.getSession().getAttribute("checkcode");
//            String aname = request.getParameter("aname");
//            String apwd = request.getParameter("apwd");
//            String yzm = request.getParameter("yzm");
//            if (!(checkcode.equals(yzm))){
//                request.setAttribute("msg", "验证码错误");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
//            Admin admin = adminService.logIn(aname, apwd);
//            if (admin != null) {
//                HttpSession httpSession = request.getSession();
//                httpSession.setAttribute("admin", admin);
//                response.sendRedirect("/servletTest03_war/index.jsp");
//                System.out.println("登陆成功");
//            } else {
//                request.setAttribute("msg", "用户名或密码错误");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
////                response.sendRedirect("/servletTest03_war/login.jsp");
//            }
//        } else if ("/logout".equals(substring)) {
//            request.getSession().invalidate();
//            response.sendRedirect("/servletTest03_war/login.jsp");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}