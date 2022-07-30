package com.huahuo.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huahuo.reggie.common.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @GetMapping
    public R<Page> page(int page, int pageSize, String number, String beginTime, String endTime)
    {

        return null;
    }
}
