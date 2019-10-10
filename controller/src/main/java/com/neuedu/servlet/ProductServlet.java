package com.neuedu.servlet;

import com.alibaba.fastjson.JSON;
import com.neuedu.entity.Category;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.service.serviceimpl.CategoryServiceImpl;
import com.neuedu.util.MyBatisUtil;
import com.neuedu.util.ServletUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @program: servletTest03
 * @description: servlet
 * @author: LinLuo
 * @create: 2019-09-16 11:26
 **/
@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决中文乱码
        ServletUtil.setCharacter(request, response);
        SqlSession session = MyBatisUtil.getSqlSession("mybatis-config.xml");
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
        ProductMapper productMapper = session.getMapper(ProductMapper.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(mapper);
        CategoryServiceImpl categoryService1 = new CategoryServiceImpl(mapper, productMapper);
        String method = request.getParameter("method");
        PrintWriter out = response.getWriter();
        // 查找全部
        if ("findToTree".equals(method)) {
            System.out.println("findToTree");
            List<Category> categories = categoryService.findToTree();
//            request.setAttribute("categories", categories);
//            request.getRequestDispatcher("category-list.jsp").forward(request, response);
            String s = JSON.toJSONString(categories);
            out.print(s);
        } else if ("subcategories".equals(method)) {
            System.out.println("subcategories");
            String categoryName = request.getParameter("categoryName");
            String categoryDescription = request.getParameter("categoryDescription");
            int categoryParentId = Integer.parseInt(request.getParameter("categoryParentId"));
            categoryService.insertChild(categoryName, categoryDescription, categoryParentId);
            session.commit();
        } else if ("load".equals(method)) {
            System.out.println("load");
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            request.getRequestDispatcher("subcategories-add.jsp").forward(request, response);
        } else if ("rootcategories".equals(method)) {
            System.out.println("rootcategories");
            String categoryName = request.getParameter("categoryName");
            String categoryDescription = request.getParameter("categoryDescription");
            categoryService.insertRoot(categoryName, categoryDescription);
            session.commit();
        } else if ("del".equals(method)) {
            categoryService1.del(1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}