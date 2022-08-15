package com.firework.controller;

import com.firework.domain.Book;
import com.firework.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.save(book);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.UPDATE_ERR,flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {

        Book book =bookService.getById(id);
        Integer code = book!=null ? Code.GET_OK : Code.GET_ERR;
        String msg = book!=null ? "查询成功 ": "数据查询失败请重试";
        return new Result(book,code,msg);
    }

    @GetMapping
    public Result getAll() {
        List<Book> all = bookService.getAll();
        Integer code = all!=null ? Code.GET_OK : Code.GET_ERR;
        String msg = all!=null ? "查询成功 ": "数据查询失败请重试";
        return new Result(all,code,msg);
    }
}
