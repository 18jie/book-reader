package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.enums.BookTypeEnum;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.User;
import com.graduation.bookreader.model.UserFavorite;
import com.graduation.bookreader.model.Weight;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.UserFavoriteMapper;
import com.graduation.bookreader.repo.WeightMapper;
import com.graduation.bookreader.util.UserSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-29
 * Time: 17:15
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Resource
    private WeightMapper weightMapper;

    @Resource
    private UserFavoriteMapper userFavoriteMapper;

    @Resource
    private UserSession userSession;

    @Override
    public IPage<Book> listBookByType(Integer type, Integer pageNum, Integer pageSize) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(type)) {
            queryWrapper.eq("type", BookTypeEnum.getTypeByType(type).name());
        }
        //全量书籍
        List<Book> books = bookMapper.selectList(queryWrapper);
        QueryWrapper<Weight> weightQueryWrapper = new QueryWrapper<>();
        //权重
        weightQueryWrapper.eq("deleted", 0);
        Weight weight = weightMapper.selectOne(weightQueryWrapper);
        List<String> weigthSort = new ArrayList<>();
        books.forEach(book -> {
            String key = (book.getBookClickCount() * weight.getClick() + book.getFavoriteCount() * weight.getFavorite() +
                    book.getLikeCount() * weight.getLike()) + "-" + book.getId();
            weigthSort.add(key);
        });
        weigthSort.sort((o1, o2) -> {
            int o1Int = Integer.parseInt(o1.substring(o1.indexOf("-", o1.length())));
            int o2Int = Integer.parseInt(o1.substring(o2.indexOf("-", o1.length())));
            if (o1Int > o2Int) {
                return 1;
            } else if (o1Int < o2Int) {
                return -1;
            }
            return 0;
        });
        //TODO 这个分页的计算可能需要修改
        Page<Book> page = new Page<>(pageNum, pageSize);
        Integer pageStart = Math.min((pageNum - 1) * pageSize, books.size());
        Integer pageEnd = Math.min(pageNum * pageSize, books.size());
        List<String> idStrs = weigthSort.subList(pageStart, pageNum);

        List<Integer> bookIds = new ArrayList<>();
        idStrs.forEach(id -> {
            String realId = id.substring(id.indexOf("-"));
            bookIds.add(Integer.parseInt(realId));
        });
        List<Book> bookList = bookMapper.selectBatchIds(bookIds);
        page.setRecords(bookList);
        page.setTotal(books.size());
        page.setSize(bookList.size());

        return page;
    }

    @Override
    public IPage<Book> listBookByName(String name, Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name", name);
        queryWrapper.orderByDesc("create_time");
        return bookMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public UserFavoriteBookVo bookDetail(Integer bookId) {
        User user = userSession.localUser();
        UserFavorite userFavorite = new UserFavorite();
        userFavorite.setUserId(user.getId());

        QueryWrapper<UserFavorite> queryWrapper = new QueryWrapper<>(userFavorite);
        List<UserFavorite> userFavorites = userFavoriteMapper.selectList(queryWrapper);
        List<Integer> books = userFavorites.stream().map(UserFavorite::getBookId).collect(Collectors.toList());
        Book book = bookMapper.selectById(bookId);

        UserFavoriteBookVo userFavoriteBookVo = new UserFavoriteBookVo();
        BeanUtils.copyProperties(book, userFavorite);
        userFavoriteBookVo.setIsFavorite(books.contains(bookId));
        return userFavoriteBookVo;
    }
}
