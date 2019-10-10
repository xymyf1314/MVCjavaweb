import com.neuedu.entity.Category;
import com.neuedu.entity.Product;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @program: parentshop
 * @description: 商品测试类
 * @author: Linluo
 * @create: 2019-10-08 18:45
 **/


public class ProductTest {
    @Test
    public void findAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession("mybatis-config.xml");
        ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> products = mapper.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }
    @Test
    public void findById(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession("mybatis-config.xml");
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        List<Category> categories = mapper.findById(4);
        System.out.println(categories.size());
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    @Test
    public void selectCategoryChildrenByPid(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession("mybatis-config.xml");
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        List<Category> categories = mapper.selectCategoryChildrenByPid(1);
        System.out.println(categories.size());
        for (Category category : categories) {
            System.out.println(category);
        }
    }

}
