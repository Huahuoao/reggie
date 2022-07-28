package com.huahuo.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.reggie.dto.DishDto;
import com.huahuo.reggie.entity.Category;
import com.huahuo.reggie.entity.Dish;
import com.huahuo.reggie.entity.DishFlavor;
import com.huahuo.reggie.mapper.CategoryMapper;
import com.huahuo.reggie.mapper.DishMapper;
import com.huahuo.reggie.service.CategoryService;
import com.huahuo.reggie.service.DishFlavorService;
import com.huahuo.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto
     */
    @Transactional  //多表操作 加入事务管理
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        Long dishId = dishDto.getId();//菜品id

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishId);
        }

        //保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);

    }

    /**
     * 查询菜品信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
       //查询菜品基本信息
        Dish dish = this.getById(id);
        //查询菜品口味信息
        DishDto dishDto =new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Transactional  //多表操作 加入事务管理
    public void updateWithFlavor(DishDto dishDto) {
       this.updateById(dishDto);  //在dish里面先update能更新的
       LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
       queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper); //先删掉原本的dishFlavor
        List<DishFlavor> flavors = dishDto.getFlavors();
        Long dishId = dishDto.getId();
        for (DishFlavor flavor : flavors) {
            flavor.setDishId(dishId);
        }
        dishFlavorService.saveBatch(flavors); //再添加新的dishFlavor
    }
}
