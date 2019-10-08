import com.neuedu.entity.Category;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class CategoryTest {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession("mybatis-config.xml");
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
       mapper.update(new Category());
    }
}
