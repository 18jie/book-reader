package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.repo.UserMapper;
import com.graduation.bookreader.util.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-09
 * Time: 16:38
 */
@Service
public class LoginServiceimpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserSession userSession;

    @Override
    public boolean doLogin(User user, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", user.getUserName());
        User realUser = userMapper.selectOne(queryWrapper);
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (realUser.getPassword().equals(md5)) {
            request.getSession().setAttribute("userId", realUser.getId());
        } else {
            return true;
        }
        return false;
    }

    @Override
    public boolean logout(User usr, HttpServletRequest request) {
        User user = userSession.localUser();
        if(user.getId().equals(usr.getId())){
            request.getSession().removeAttribute("userId");
            return true;
        }
        return false;
    }
}
