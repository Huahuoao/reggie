package com.huahuo.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.huahuo.reggie.common.CustomException;
import com.huahuo.reggie.common.R;
import com.huahuo.reggie.dto.DishDto;
import com.huahuo.reggie.dto.SetmealDto;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.Dish;
import com.huahuo.reggie.entity.Setmeal;
import com.huahuo.reggie.entity.SetmealDish;
import com.huahuo.reggie.mapper.SetMealMapper;
import com.huahuo.reggie.service.CategoryService;
import com.huahuo.reggie.service.SetMealDishService;
import com.huahuo.reggie.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetMealController {
    @Autowired
    SetMealService setMealService;
    @Autowired
    CategoryService categoryService;
    @Resource
    private SetMealMapper setMealMapper;
    @Autowired
    private SetMealDishService setMealDishService;

// 优化
    @GetMapping("/page")
    public R<IPage> page(int page, int pageSize, String name)
    {
        //连表查询 分页
        IPage<SetmealDto> orderPage =setMealMapper.selectJoinPage( //主表的Mapper调用方法
                new Page<SetmealDto>(page,pageSize),  //搞个分页选择器
                SetmealDto.class,  //接收类
                new MPJLambdaWrapper<Setmeal>()  //join分页查询
                        .selectAll(Setmeal.class) //Setmeal里面全部都要
                        .select(Category::getName) //Category里面只要name
                        .selectAs(Category::getName, SetmealDto::getCategoryName) //把两个name的名称对应上
                        .leftJoin(Category.class, Category::getId, Setmeal::getCategoryId) //左连接 ID 对应 ID
                        .orderByAsc(Setmeal::getUpdateTime));//加一个排序条件

               return R.success(orderPage);
    }

@PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto)
{
      setMealService.saveWithDish(setmealDto);
      return R.success("新增套餐成功！");
}

    /**删除
     *
     * @param ids
     * @return
     */
//    用@RequestParam请求接口时,URL是:
//    http://www.test.com/user/getUserById?userId=1
//    用@PathVariable请求接口时,
//    URL是:http://www.test.com/user/getUserById/2

    @DeleteMapping
    @Transactional
    public R<String> save(@RequestParam List<Long> ids)
{
      setMealService.removeWithDish(ids);

      return R.success("删除成功");
}

@PostMapping("/status/0")
    public R<String> stop(@RequestParam List<Long> ids)
{
    for (Long id : ids) {
        Setmeal setmeal = setMealService.getById(id);
        setmeal.setStatus(0);
        setMealService.updateById(setmeal);
    }
    return  R.success("停售成功");
}

    @PostMapping("/status/1")
    public R<String> on(@RequestParam List<Long> ids)
    {
        for (Long id : ids) {
            Setmeal setmeal = setMealService.getById(id);
            setmeal.setStatus(1);
            setMealService.updateById(setmeal);
        }
        return  R.success("启售成功");
    }
}
