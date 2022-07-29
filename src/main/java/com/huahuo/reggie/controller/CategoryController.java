package com.huahuo.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huahuo.reggie.common.R;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.service.CategoryService;
import com.huahuo.reggie.service.DishService;
import com.huahuo.reggie.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController  // 返回json + 这是一个Controller
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    DishService dishService;
    @Autowired
    CategoryService categoryService;
   @Autowired
    SetMealService setMealService;
    /**
     * 新增分类
     * @param request
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Category category){
        log.info("新增分类，分类信息：{}",category.toString());
        categoryService.save(category);
        return R.success("新增分类成功！");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize)
    {
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加一个排序条件
        queryWrapper.orderByAsc(Category::getSort);
        queryWrapper.ne(Category::getIsDeleted,1);
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);

    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> remove(Long ids)
    {
       categoryService.remove(ids);
       return R.success("删除分类成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category)
    {
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    //http://localhost:8080/category/list?type=1
    @GetMapping("/list")
    public R<List<Category>> list( Category category)
    {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        queryWrapper.ne(Category::getIsDeleted,1);
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }


}
