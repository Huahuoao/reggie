import com.itheima.mapper.AnimalMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Animal;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class MyBatisDemo2 {
    public static void main(String[] args) throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(); //获取一个sqlSession对象来执行sql
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); //这个sql由Mapper代理执行 这个可以理解为创建一个代理对象然后把接口里的方法实现（通过UserMapper.xml）
        AnimalMapper animalMapper = sqlSession.getMapper(AnimalMapper.class);
        List<Animal> animals = animalMapper.selectAll();
        List<User> users = userMapper.selectAll();
        System.out.println(users);
        System.out.println();
        System.out.println(animals);
        sqlSession.close();
    }
}
