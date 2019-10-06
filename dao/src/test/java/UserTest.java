import com.neuedu.entity.User;
import com.neuedu.mapper.UserMapper;
import com.neuedu.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserTest {
    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession("mybatis-config.xml");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        Timestamp timestamp = Timestamp.valueOf("2019-10-02 11:44:47.0");
//        mapper.rollback(new User(5,"222","4a9bd19b3b8676199592a346051f950c",0,"2","2",timestamp,0));
//        List<User> all = mapper.findAll();
//        for (User user : all) {
//            System.out.println(user);
//        }
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }


    }
}
