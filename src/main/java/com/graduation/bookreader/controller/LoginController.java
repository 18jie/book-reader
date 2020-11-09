package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.User;
import com.graduation.bookreader.service.LoginService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-09
 * Time: 16:33
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/doLogin")
    public Result login(@RequestBody User user, HttpServletRequest httpServletRequest) {
        if (loginService.doLogin(user, httpServletRequest)) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody User user, HttpServletRequest request) {
        if (loginService.logout(user, request)) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

}
