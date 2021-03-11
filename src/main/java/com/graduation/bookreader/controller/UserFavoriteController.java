package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;
import com.graduation.bookreader.service.UserFavoriteService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-22
 * Time: 20:14
 */
@RestController
@RequestMapping("/favorite")
public class UserFavoriteController {

    @Resource
    private UserFavoriteService userFavoriteService;

    @GetMapping("/list")
    public Result favoriteList() {
        return Result.success(userFavoriteService.list());
    }

    @GetMapping("/addOrRemoveFavorite")
    public Result addOrRemoveFavorite(Integer bookId) {
        return Result.success(userFavoriteService.addOrRemoveFavorite(bookId));
    }

    @GetMapping("/userFavorites")
    public Result userFavorites(QueryParam queryParam) {
        return Result.success(userFavoriteService.userFavorite(queryParam));
    }

    @PostMapping("/deleteFavorites")
    public Result deleteFavorites(@RequestBody BookUnUpParam bookUnUpParam){
        userFavoriteService.deleteFavorites(bookUnUpParam);
        return Result.success();
    }

    @GetMapping("/listFavoriteBookVo")
    public Result listFavoriteBookVo(Integer pageNum){
        return Result.success(userFavoriteService.listFavoriteBookVo(pageNum));
    }


}

