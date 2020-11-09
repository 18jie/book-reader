package com.graduation.bookreader.util;

import com.graduation.bookreader.model.User;
import com.graduation.bookreader.repo.UserMapper;
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

    @Resource
    private UserMapper userMapper;

    public User localUser(){
        Integer userId = UserThreadLocalContext.getUser();
        if(userId == null){
            return null;
        }
        return userMapper.selectById(userId);
    }

}
