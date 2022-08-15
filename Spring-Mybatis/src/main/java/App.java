import com.itheima.config.SpringConfig;
import com.itheima.domain.User;
import com.itheima.service.LogService;
import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
       UserService userService = ctx.getBean(UserService.class);
        userService.Transfer("陈建辉","马露露",50);
//        List<User> users =  userService.selectAll();
//        System.out.println(users);

         LogService logService = ctx.getBean(LogService.class);
         logService.log("马露露","陈建辉",50);
         //logService.truncate();
    }
}
