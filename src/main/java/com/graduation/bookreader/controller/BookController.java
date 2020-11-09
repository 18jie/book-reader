package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.service.BookService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-29
 * Time: 17:14
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/listBooks")
    public Result getSortBooksByType(Integer type, Integer pageNum, Integer pageSize) {
        return Result.success(bookService.listBookByType(type, pageNum, pageSize));
    }

    @GetMapping("/listBooksByName")
    public Result getBookByName(String name, Integer pageNum, Integer pageSize) {
        return Result.success(bookService.listBookByName(name, pageNum, pageSize));
    }

    @GetMapping("/getBookById")
    public Result getBookById(Integer id) {
        return Result.success(bookService.getBookById(id));
    }

    @GetMapping("/bookDetail")
    public Result bookDetail(Integer bookId) {
        return Result.success(bookService.bookDetail(bookId));
    }

    @PostMapping("/addBook")
    public Result addBook(@RequestBody Book book){
        return null;
    }

    @PostMapping("updateBook")
    public Result updateBook(@RequestBody Book book){
        return null;
    }

}
