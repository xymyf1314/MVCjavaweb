import com.neuedu.entity.Category;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryTest {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession("mybatis-config.xml");
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
        List<Category> toTree = mapper.findToTree();
        for (Category category : toTree) {
            System.out.println(category);
        }
    }
}
