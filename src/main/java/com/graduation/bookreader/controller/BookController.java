package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;
import com.graduation.bookreader.model.vo.BookDetailVo;
import com.graduation.bookreader.model.vo.BookVo;
import com.graduation.bookreader.service.BookService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 后台接口
     * @param type
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/listBooks")
    public Result getSortBooksByType(Integer type, String name, Integer pageIndex, Integer pageSize) {
        return Result.success(bookService.listBookByType(type, name, pageIndex, pageSize));
    }

    @PostMapping("/unUpBook")
    public Result unUpBookById(@RequestBody BookUnUpParam bookUnUpParam){
        bookService.unUpBookByIds(bookUnUpParam.getIds());
        return Result.success();
    }

    @GetMapping("/listBooksByName")
    public Result getBookByName(String name, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
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
    public Result addBook(@RequestBody Book book) {
        return null;
    }

    @PostMapping("updateBook")
    public Result updateBook(@RequestBody Book book) {
        return null;
    }

    @GetMapping("/hostTypes")
    public Result hotTypes() {
        List<Map<String, Object>> hostTypes = new ArrayList<>();
        Map<String, Object> one = new HashMap<>();
        one.put("id", 1);
        one.put("_id", 1);
        one.put("title", "火爆全网");
        Map<String, Object> two = new HashMap<>();
        two.put("id", 2);
        two.put("_id", 2);
        two.put("title", "最新更新");
        hostTypes.add(one);
        hostTypes.add(two);
        return Result.success(hostTypes);
    }

    @GetMapping("/hotBooks/{type}")
    public Result<List<BookVo>> hostBooks(@PathVariable Integer type) {
        return Result.success(bookService.hotBooks(type));
    }

    @GetMapping("/bookVo/{id}")
    public Result<BookVo> bookVo(@PathVariable Integer id) {
        return Result.success(bookService.bookVoById(id));
    }

    @GetMapping("/bookDetailVo/{id}")
    public Result<BookDetailVo> bookDetailVo(@PathVariable Integer id) {
        return Result.success(bookService.bookDetailVo(id));
    }

    @GetMapping("/searchBookByName")
    public Result searchBookByName(String name) {
        return Result.success(bookService.searchBookByName(name));
    }


    @GetMapping("/bookVosByPage/{type}/{pageNum}")
    public Result bookVosByPage(@PathVariable Integer type, @PathVariable Integer pageNum) {
        return Result.success(bookService.bookVosByPage(type, pageNum));
    }

    @GetMapping("/bookTypes")
    public Result bookTypes() {
        return Result.success(bookService.getBookTypes());
    }


}
