package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.User;
import com.graduation.bookreader.service.UserService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-22
 * Time: 20:33
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (userService.register(user)) {
            return Result.success();
        }
        return Result.fail();
    }


}
