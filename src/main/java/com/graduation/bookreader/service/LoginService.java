package com.graduation.bookreader.service;

import com.graduation.bookreader.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-09
 * Time: 16:37
 */
public interface LoginService {

    User doLogin(User user, HttpServletRequest request);

    boolean logout(User usr, HttpServletRequest request);

    User isLogin();

}
