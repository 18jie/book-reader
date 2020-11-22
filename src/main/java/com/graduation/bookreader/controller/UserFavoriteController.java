package com.graduation.bookreader.controller;

import com.graduation.bookreader.service.UserFavoriteService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
