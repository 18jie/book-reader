package com.graduation.bookreader.util;

import com.graduation.bookreader.model.User;
import com.graduation.bookreader.repo.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * 用户工具类
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 22:11
 */
@Component
public class UserSession {
    private final static Logger logger = LoggerFactory.getLogger(UserSession.class);

    @Resource
    private UserMapper userMapper;

    public User localUser() {
        Integer userId = UserThreadLocalContext.getUser();
        logger.info("UserSession userId = {}", userId);
        if (userId == null) {
            return null;
        }
        return userMapper.selectById(userId);
    }

    public void removeUser(){
        UserThreadLocalContext.clear();
    }

}
