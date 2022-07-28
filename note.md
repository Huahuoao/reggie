# 瑞吉外卖(后端)

### 开发环境搭建1

##### 数据库环境搭建

- employee 员工表
- category 菜品和套餐分类表
- dish 菜品表
- setmeal 套餐表
- setmeal_dish 套餐菜品关系表
- dish_flavor 菜品口味关系表
- user 用户表
- address_book 地址表
- shopping_cart 购物车表
- orders 订单表
- order_detail 订单明细表

<img src="I:\瑞吉外卖\images\数据库结构.png" alt="数据库结构" style="zoom:;" />

##### Maven项目搭建

-  pom

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.7.2</version>
          <relativePath/> <!-- lookup parent from repository -->
      </parent>
      <groupId>com.huahuo</groupId>
      <artifactId>reggie</artifactId>
      <version>1.0-SNAPSHOT</version>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
              <java.version>1.8</java.version>
      </properties>
  
      <dependencies>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter</artifactId>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
          </dependency>
  
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
              <scope>compile</scope>
          </dependency>
  
          <dependency>
              <groupId>com.baomidou</groupId>
              <artifactId>mybatis-plus-boot-starter</artifactId>
              <version>3.4.2</version>
          </dependency>
  
          <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <version>1.18.20</version>
          </dependency>
  
          <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>fastjson</artifactId>
              <version>1.2.76</version>
          </dependency>
  
          <dependency>
              <groupId>commons-lang</groupId>
              <artifactId>commons-lang</artifactId>
              <version>2.6</version>
          </dependency>
  
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <scope>runtime</scope>
          </dependency>
  
          <dependency>
              <groupId>com.alibaba</groupId>
              <artifactId>druid-spring-boot-starter</artifactId>
              <version>1.1.23</version>
          </dependency>
  
      </dependencies>
  
      <build>
          <plugins>
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <version>2.7.2</version>
              </plugin>
          </plugins>
      </build>
  
  </project>
  ```

-  yml

```yml
server:
  port: 8080
spring:
  application:
    name: reggie
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/reggie?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
      username: root
      password: 12345678
mybatis-plus:
  configuration:
    #在映射实体或者属性时，将数据库中表名和字段名中的下划线去掉，按照驼峰命名法映射
    map-underscore-to-camel-case: true
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      id-type: ASSIGN_ID
```

- 启动类

```java
@SpringBootApplication
@Slf4j  //来自lombok 
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功");
    }
}
```

- 导入静态资源
- 进行静态资源映射 避免被SpringMVC拦截

```java
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 设置静态资源映射
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
    }
}
```

- 创建项目结构
- LambdaQueryWrapper<Employee>

```txt
where: WHERE 语句，拼接 + WHERE 条件
and: AND 语句，拼接 + AND 字段=值
andNew: AND 语句，拼接 + AND (字段=值)
or: OR 语句，拼接 + OR 字段=值
orNew: OR 语句，拼接 + OR (字段=值)
eq: 等于=
allEq: 基于 map 内容等于=
ne: 不等于<>
gt: 大于>
ge: 大于等于>=
lt: 小于<
le: 小于等于<=
like: 模糊查询 LIKE
notLike: NOT LIKE模糊查询
in: IN 查询
notIn: NOT IN 查询
isNull: NULL 值查询
isNotNull: IS NOT NULL
groupBy: 分组 GROUP BY
having: HAVING 关键词
orderBy: 排序 ORDER BY
orderAsc: Asc 排序 ORDER BY
orderDesc: DESC 排序 ORDER BY
exists: EXISTS 条件语句
notExists: NOT EXISTS 条件语句
between: BETWEEN 条件语句
notBetween: NOT BETWEEN 条件语句
addFilter: 自由拼接 SQL
last: 拼接在最后，例如：last(“LIMIT 1”)
```

##### 用户登录功能开发

- entity实体类 Employee
- mapper @Mapper extends Basemapper （使用mybatis-plus）
- service 继承 Iservice
- impl 继承 ServiceImpl
- 最重要的Controller

```java
/**
 * 员工登录
 * @param request
 * @param employee
 * @return
 */
@PostMapping("/login")
public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){

    //1、将页面提交的密码password进行md5加密处理
    String password = employee.getPassword();
    password = DigestUtils.md5DigestAsHex(password.getBytes());

    //2、根据页面提交的用户名username查询数据库
    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Employee::getUsername,employee.getUsername());
    Employee emp = employeeService.getOne(queryWrapper);

    //3、如果没有查询到则返回登录失败结果
    if(emp == null){
        return R.error("登录失败");
    }

    //4、密码比对，如果不一致则返回登录失败结果
    if(!emp.getPassword().equals(password)){
        return R.error("登录失败");
    }

    //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
    if(emp.getStatus() == 0){
        return R.error("账号已禁用");
    }

    //6、登录成功，将员工id存入Session并返回登录成功结果
    request.getSession().setAttribute("employee",emp.getId());
    return R.success(emp);
}
```

![登录界面](I:\瑞吉外卖\images\登录界面.png)

##### 用户退出功能

```java
/**
 * 员工退出
 * @param request
 * @return
 */
@PostMapping("logout")
public R<String> logout(HttpServletRequest request) {
  //清理Session中保存的当前员工的id
    request.getSession().removeAttribute("employee");
    return R.success("退出成功");

}
```

##### 拦截器（判断用户是否登录）

>@ServletComponentScan  Application要添加这个注解 要不然读取不到这个拦截器

```java
@WebFilter(filterName = "loginCheckFilterer",urlPatterns = "/*")
@Slf4j

public class LoginCheckFilterer implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取本次请求的URI
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
         if(check(urls,requestURI))
         { filterChain.doFilter(request,response);  return;}
      if(request.getSession().getAttribute("employee")!=null)
      {
          filterChain.doFilter(request,response);  return;
      }
      response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }


    /**
     * 判断本次请求是否放行
     * @param requestURI
     * @param urls
     * @return
     */
    public boolean check(String[] urls,String requestURI)
    {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match == true) return true;
        }

        return false;
    }
}
```

##### 新增员工

```java
@PostMapping
public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
    log.info("新增员工，员工信息：{}",employee.toString());

    //设置初始密码123456，需要进行md5加密处理
    employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

    //employee.setCreateTime(LocalDateTime.now());
    //employee.setUpdateTime(LocalDateTime.now());

    //获得当前登录用户的id
    //Long empId = (Long) request.getSession().getAttribute("employee");

    //employee.setCreateUser(empId);
    //employee.setUpdateUser(empId);

    employeeService.save(employee);

    return R.success("新增员工成功");
}
```

![新增员工](I:\瑞吉外卖\images\新增员工.png)

##### 分页查询

```java
/**
 * 分页查询
 * @return
 */@GetMapping("/page")
public R<Page> page(int page,int pageSize,String name)
{
 log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
 //构造分页构造器
    Page pageInfo = new Page(page,pageSize);
    //构造条件构造器
    LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
    if(name!=null)
    queryWrapper.like(Employee::getName,name);
    //添加一个排序条件
    queryWrapper.orderByDesc(Employee::getUpdateTime);
    employeeService.page(pageInfo,queryWrapper);
    return R.success(pageInfo);

}
```

![分页查询](I:\瑞吉外卖\images\分页查询.png)

##### 修改员工信息

```java
/**
 * 根据id修改员工信息
 * @param employee
 * @return
 */
@PutMapping
public R<String> update(HttpServletRequest request,@RequestBody Employee employee)
{
    log.info(employee.toString());
    employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
    employee.setUpdateTime(LocalDateTime.now());
    employeeService.updateById(employee);
    return R.success("员工信息修改成功");
}
```

![修改员工信息](I:\瑞吉外卖\images\修改员工信息.png)

> 因为id为long类型，在前端处理的时候转换为json数据会进行精度损失，所以要设计一个对象映射器，基于jackson将Java对象转化为json，这样就避免了精度损失，导致无法匹配。工具类直接用就行了。导入工具类 再在SpringMvcConfig里面配置这个映射器为优先，即可启用。

- 工具类

```java
/**
 * 对象映射器:基于jackson将Java对象转为json，或者将json转为Java对象
 * 将JSON解析为Java对象的过程称为 [从JSON反序列化Java对象]
 * 从Java对象生成JSON的过程称为 [序列化Java对象到JSON]
 */
public class JacksonObjectMapper extends ObjectMapper {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    public JacksonObjectMapper() {
        super();
        //收到未知属性时不报异常
        this.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        //反序列化时，属性不存在的兼容处理
        this.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        SimpleModule simpleModule = new SimpleModule()
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)))

                .addSerializer(BigInteger.class, ToStringSerializer.instance)
                .addSerializer(Long.class, ToStringSerializer.instance)
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));

        //注册功能模块 例如，可以添加自定义序列化器和反序列化器
        this.registerModule(simpleModule);
    }
}
```

- MVC配置

```java
@Override
protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
  log.info("拓展消息转换....");
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    //设置对象转换器，底层使用JACKson将java对象转换为json
    messageConverter.setObjectMapper(new JacksonObjectMapper());
    converters.add(0,messageConverter);
}
```

##### 查询员工信息

```java
/**
 * 根据id查询用户信息
 * @param id
 * @return
 */
@GetMapping("/{id}")
public R<Employee> getById(@PathVariable Long id)
{
    log.info("根据id查询员工信息...");
    Employee employee = employeeService.getById(id);
    if(employee!=null)
    return R.success(employee);
    return R.error("没有查询到员工");
}
```

##### 全局异常处理类

> 对整个项目的异常进行一个接收和处理

```java
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> ExceptionHandler(SQLIntegrityConstraintViolationException ex)
    {
        log.error(ex.getMessage());
        //判断元素是否在动态数组中
       if(ex.getMessage().contains("Duplicate entry"))
       {
           String[] split = ex.getMessage().split(" ");
           String msg = "用户 "+split[2] + "已存在";
           return  R.error(msg);
       }
      return R.error("未知错误");
    }
}
```

##### 对全局的通用变量进行自动填充

- createTime
- updateTime
- creteUser
- updateUser

> 这也是基于Mybatis Plus的一个实现
>
> 首先要在需要自动填充的元素头添加注解
>
>   @TableField(fill = FieldFill.INSERT)
>
>   @TableField(fill = FieldFill.INSERT_UPDATE)

---

```java
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入操作，自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("createUser",BaseContext.getCurrentId());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());
    }

    /**
     * 更新操作，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);

        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContext.getCurrentId());
    }
}
```

> 上述代码中为了获取ID，采用了获取线程中的ID的方法  下面是详细的介绍 因为在一个用户访问页面的时候，进行增删改查操作，都是开启的同一个线程，所以就可以在登录的时候从线程中获取ID，储存起来，然后到这边调用。基于 ThreadLocal 这是JDK提供的一个类

```java
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
```

接着，在过滤器中修改 方法 如果已经登录 那就在线程中存入这个id值

```java
if(request.getSession().getAttribute("employee")!=null)
{
    Long empId= (Long) request.getSession().getAttribute("employee");
    BaseContext.setCurrentId(empId);
    filterChain.doFilter(request,response);  //放行
    return;
}
```

##### 分类信息分页查询
