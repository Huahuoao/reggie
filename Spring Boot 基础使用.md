# Spring Boot 基础使用



#### application.properties

>```
>server.port=80
>```

#### application.yml

> ```
> server:
>   port: 80
> ```

#### application.yaml

>```
>server:
>  port: 8088
>```

- 我们以后主要用yml格式的

- 如果三个文件都有 那么properties为主启动文件

- yml为第二加载

- yaml为第三加载

  

## yaml语法规则

- 大小写敏感
- 属性层级多行描述，不同层级用空格数来划分
- 属性值前面一定要添加空格
- 井号表示注释 #
- 数组格式

## yaml数据读取方式

-  @Value("${lesson}")  直接调用 单层

-  @Value("${server.port}")  多层采用点调用
-  @Value("${enterprice.subject[0]}") 调用数组