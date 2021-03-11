package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;
import com.graduation.bookreader.service.UserService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public Result listUsers(QueryParam queryParam) {
        return Result.success(userService.listUsers(queryParam));
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestBody BookUnUpParam bookUnUpParam) {
        userService.deleteUser(bookUnUpParam);
        return Result.success();
    }

}
