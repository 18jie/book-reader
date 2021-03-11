package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.User;
import com.graduation.bookreader.service.LoginService;
import com.graduation.bookreader.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private LoginService loginService;

    @PostMapping("/doLogin")
    public Result login(@RequestBody User user, HttpServletRequest httpServletRequest) {
        return Result.success(loginService.doLogin(user, httpServletRequest));
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody User user, HttpServletRequest request) {
        if (loginService.logout(user, request)) {
            return Result.success();
        }
        return Result.fail();
    }

    @GetMapping("/isLogin")
    public Result isLogin(){
        return Result.success(loginService.isLogin());
    }

}