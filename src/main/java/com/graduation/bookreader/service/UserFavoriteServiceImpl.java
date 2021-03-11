package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.UserFavorite;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;
import com.graduation.bookreader.model.vo.BookVo;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.UserFavoriteMapper;
import com.graduation.bookreader.util.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private final static Logger logger = LoggerFactory.getLogger(UserFavoriteServiceImpl.class);

    @Resource
    private BookMapper bookMapper;

    @Resource
    private UserFavoriteMapper userFavoriteMapper;

    @Resource
    private BookService bookService;

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
        logger.info("favorite books:{}", books);
        return books;
    }

    @Override
    public boolean addOrRemoveFavorite(Integer bookId) {
        Book book = bookMapper.selectById(bookId);
        if (book == null) {
            return false;
        }
        UserFavorite userFavorite = new UserFavorite();
        userFavorite.setBookId(bookId);
        userFavorite.setUserId(userSession.localUser().getId());
        userFavorite.setDeleted(0);
        UserFavorite userFavorite1 = userFavoriteMapper.selectOne(new QueryWrapper<>(userFavorite));
        if (userFavorite1 != null) {
            userFavorite1.setDeleted(1);
            userFavoriteMapper.updateById(userFavorite1);
            return true;
        }
        userFavoriteMapper.insert(userFavorite);
        return true;
    }

    @Override
    public IPage<UserFavoriteBookVo> userFavorite(QueryParam queryParam) {
        User user = userSession.localUser();
        Page<UserFavoriteBookVo> page = new Page<>(queryParam.getPageIndex(), queryParam.getPageSize());
        return userFavoriteMapper.listFavorite(page, user.getId(), queryParam.getName());
    }

    @Override
    public IPage<BookVo> listFavoriteBookVo(Integer pageNum) {
        Page<UserFavoriteBookVo> page1 = new Page<>(pageNum, 10);
        User user = userSession.localUser();
        IPage<UserFavoriteBookVo> page = userFavoriteMapper.listFavorite(page1, user.getId(), null);
        Page<BookVo> bookVoPage = new Page<>();
        BeanUtils.copyProperties(page, bookVoPage);
        List<BookVo> bookVos = new ArrayList<>();
        page.getRecords().forEach(book -> {
            bookVos.add(bookService.bookVoById(book.getId()));
        });
        bookVoPage.setRecords(bookVos);
        return bookVoPage;
    }

    @Override
    public void deleteFavorites(BookUnUpParam bookUnUpParam) {
        User user = userSession.localUser();
        List<Integer> ids = bookUnUpParam.getIds();
        ids.forEach(id -> {
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setId(id);
            userFavorite.setUserId(user.getId());
            userFavorite.setDeleted(1);
            userFavoriteMapper.updateById(userFavorite);
        });
    }
}
