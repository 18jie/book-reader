package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.UserFavorite;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.UserFavoriteMapper;
import com.graduation.bookreader.util.UserSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-22
 * Time: 20:16
 */
@Service
public class UserFavoriteServiceImpl implements UserFavoriteService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserFavoriteMapper userFavoriteMapper;

    @Resource
    private UserSession userSession;

    @Override
    public List<Book> list() {
        User user = userSession.localUser();
        if (user == null) {
            return null;
        }
        UserFavorite query = new UserFavorite();
        query.setUserId(user.getId());
        List<UserFavorite> userFavorites = userFavoriteMapper.selectList(new QueryWrapper<>(query));
        List<Integer> bookIds = userFavorites.stream().map(UserFavorite::getBookId).collect(Collectors.toList());
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", bookIds);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }
}
