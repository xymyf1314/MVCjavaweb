/*
 　　　　　　　 ┏┓　 ┏┓+ +
 　　　　　　　┏┛┻━━━┛┻┓ + +
 　　　　　　　┃　　　　　　┃ 　
 　　　　　　　┃　　　━　　 ┃ ++ + + +
 　　　　　　 ████━████  ┃+
 　　　　　　　┃　　　　　　　┃ +
 　　　　　　　┃　　　┻　　　┃
 　　　　　　　┃　　　　　　┃ + +
 　　　　　　　┗━┓　　　┏━┛
 　　　　　　　　 ┃　　　┃　　　　　　　　　　　
 　　　　　　　　 ┃　　　┃ + + + +
 　　　　　　　　 ┃　　　┃　　　　Code is far away from bug with the animal protecting　　　　　　　
 　　　　　　　　 ┃　　　┃ + 　　　　神兽保佑,代码无bug　　
 　　　　　　　　 ┃　　　┃
 　　　　　　　　 ┃　　　┃　　+　　　　　　　　　
 　　　　　　　　 ┃　 　 ┗━━━┓ + +
 　　　　　　　　 ┃ 　　　　   ┣┓
 　　　　　　　　 ┃ 　　　　　 ┏┛
 　　　　　　　　 ┗┓┓┏━┳┓┏┛ + + + +
 　　　　　　　　  ┃┫┫ ┃┫┫
 　　　　　　　　  ┗┻┛ ┗┻┛+ + + +
 */

import com.neuedu.mapper.CategoryMapper;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.service.serviceimpl.CategoryServiceImpl;
import com.neuedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

/**
 * @program: parentshop
 * @description:
 * @author: Linluo
 * @create: 2019-10-10 08:57
 **/


public class CategoryTest {
    @Test
    void del() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession("mybatis-config.xml");
        CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryMapper, productMapper);
        categoryService.del(1);
        sqlSession.commit();
    }
}
