package com.itheima.test;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
   @Test
    public void testSelectAll() throws IOException {
       String resource = "mybatis-config.xml";
       InputStream inputStream = Resources.getResourceAsStream(resource);
       SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
       SqlSession sqlSession = sqlSessionFactory.openSession();
       BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
       List<Brand> brands = brandMapper.SelectAll();
       System.out.println(brands);
       sqlSession.close();
   }


   @Test
   public void testSelectById() throws IOException {
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
      Brand brand = brandMapper.SelectById(1);
      System.out.println(brand);
      sqlSession.close();
   }

   @Test
   public void testSelectByCondition() throws IOException {
      int status =1;
      String companyName = "华为";
      String brandName= "华为";
      companyName = "%"+companyName+"%";
     // brandName = "%" + brandName + "%";
      Map map = new HashMap();
      map.put("status",status);
      map.put("company_name",companyName);
    //  map.put("brand_name",brandName);
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
      List<Brand> brands = brandMapper.selectByCondition(map);
      System.out.println(brands);
      sqlSession.close();
   }

   @Test
   public void testSelectByConditionSingle() throws IOException {
      int status =1;
      String companyName = "华为";
      String brandName= "华为";
      companyName = "%"+companyName+"%";
       brandName = "%" + brandName + "%";
   Brand brand = new Brand();
   brand.setBrand_name(brandName);
    brand.setStatus(status);
   brand.setCompany_name(companyName);

      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
      List<Brand> brands = brandMapper.selectByConditionSingle(brand);
      System.out.println(brands);
      sqlSession.close();
   }
@Test
   public void testAdd() throws IOException {
      //接收参数
      int status = 1;
      String companyName = "波导手机";
      String brandName = "波导";
      String description = "手机中的战斗机";
      int ordered = 100;


      //封装对象
      Brand brand = new Brand();
      brand.setStatus(status);
      brand.setCompany_name(companyName);
      brand.setBrand_name(brandName);
      brand.setDescription(description);
      brand.setOrdered(ordered);

      //1. 获取SqlSessionFactory
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      //2. 获取SqlSession对象
      SqlSession sqlSession = sqlSessionFactory.openSession();
      //SqlSession sqlSession = sqlSessionFactory.openSession(true);

      //3. 获取Mapper接口的代理对象
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

      //4. 执行方法

      brandMapper.add(brand);

      //提交事务
      sqlSession.commit();

      //5. 释放资源
      sqlSession.close();

   }

   @Test
   public void testAdd2() throws IOException {
      //接收参数
      int status = 1;
      String companyName = "波导手机";
      String brandName = "波导";
      String description = "手机中的战斗机";
      int ordered = 100;


      //封装对象
      Brand brand = new Brand();
      brand.setStatus(status);
      brand.setCompany_name(companyName);
      brand.setBrand_name(brandName);
      brand.setDescription(description);
      brand.setOrdered(ordered);

      //1. 获取SqlSessionFactory
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      //2. 获取SqlSession对象
      SqlSession sqlSession = sqlSessionFactory.openSession();
      //SqlSession sqlSession = sqlSessionFactory.openSession(true);

      //3. 获取Mapper接口的代理对象
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

      //4. 执行方法

      brandMapper.add(brand);
      System.out.println(brand.getId());
      //提交事务
      sqlSession.commit();

      //5. 释放资源
      sqlSession.close();

   }


   @Test
   public void testUpdate() throws IOException {
      //接收参数
      int status = 1;
      String companyName = "波导手机";
      String brandName = "波导";
      String description = "波导手机，手机中的战斗机";
      int ordered = 100;
      int id=5;

      //封装对象
      Brand brand = new Brand();
      brand.setStatus(status);
      brand.setCompany_name(companyName);
      brand.setBrand_name(brandName);
      brand.setDescription(description);
      brand.setOrdered(ordered);
      brand.setId(id);
      //1. 获取SqlSessionFactory
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      //2. 获取SqlSession对象
      SqlSession sqlSession = sqlSessionFactory.openSession();
      //SqlSession sqlSession = sqlSessionFactory.openSession(true);

      //3. 获取Mapper接口的代理对象
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

      //4. 执行方法

      brandMapper.update(brand);

      //提交事务
      sqlSession.commit();

      //5. 释放资源
      sqlSession.close();

   }

   @Test
   public void testDeleteById() throws IOException {
      Integer id = 5;
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
      brandMapper.deleteById(id);
      sqlSession.commit();
      sqlSession.close();
   }

   @Test
   public void testDeleteByIds() throws IOException {
      int [] ids = {4,9,10};
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
      brandMapper.deleteByIds(ids);
      sqlSession.commit();
      sqlSession.close();
   }
   @Test
   public void testUserSelectById() throws IOException {
       Integer id = 1;
      String resource = "mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      SqlSession sqlSession = sqlSessionFactory.openSession();
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      User user = userMapper.selectById(id);
      System.out.println(user);
      sqlSession.close();
   }
}
