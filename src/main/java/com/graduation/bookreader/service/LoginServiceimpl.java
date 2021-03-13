package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.repo.UserMapper;
import com.graduation.bookreader.util.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-09
 * Time: 16:38
 */
@Service
public class LoginServiceimpl implements LoginService {
    private final static Logger logger = LoggerFactory.getLogger(LoginServiceimpl.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserSession userSession;

    @Override
    public User doLogin(User user, HttpServletRequest request) {
        logger.info("user={}", user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", user.getPhone());
        User realUser = userMapper.selectOne(queryWrapper);
        String md5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (realUser.getPassword().equals(md5)) {
            logger.info("存入session={}", realUser.getId());
            request.getSession().setAttribute("userId", realUser.getId());
            realUser.setLoginTime(new Date(System.currentTimeMillis()));
            realUser.setLoginCount(realUser.getLoginCount() + 1);
            userMapper.updateById(realUser);
            return realUser;
        }
        return null;
    }

    @Override
    public boolean logout(User usr, HttpServletRequest request) {
        User user = userSession.localUser();
        userSession.removeUser();
        if (user.getId().equals(usr.getId())) {
            request.getSession().removeAttribute("userId");
            return true;
        }
        return false;
    }

    @Override
    public User isLogin() {
        return userSession.localUser();
    }
}
