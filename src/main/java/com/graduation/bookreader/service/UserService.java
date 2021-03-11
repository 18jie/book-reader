package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-22
 * Time: 20:35
 */
public interface UserService {

    boolean register(User user);

    IPage<User> listUsers(QueryParam queryParam);

    void updateUser(User user);

    void deleteUser(BookUnUpParam bookUnUpParam);

}
