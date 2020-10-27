package com.graduation.bookreader.util;

import com.graduation.bookreader.model.User;

/**
 * Description:
 * 本地线程用户工具类
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 21:58
 */
public class UserThreadLocalContext {

    private static ThreadLocal<Integer> userThreadLocal = new ThreadLocal<>();

    public static void init(Integer user){
        userThreadLocal.set(user);
    }

    public static Integer getUser(){
        return userThreadLocal.get();
    }

    public static void clear(){
        userThreadLocal.remove();
    }

}
