package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.enums.BookTypeEnum;
import com.graduation.bookreader.model.*;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.ChapterMapper;
import com.graduation.bookreader.repo.UserFavoriteMapper;
import com.graduation.bookreader.repo.WeightMapper;
import com.graduation.bookreader.util.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Resource
    private BookMapper bookMapper;

    @Resource
    private WeightMapper weightMapper;

    @Resource
    private UserFavoriteMapper userFavoriteMapper;

    @Resource
    private UserSession userSession;

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public IPage<Book> listBookByType(Integer type, Integer pageNum, Integer pageSize) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (Objects.nonNull(type)) {
            logger.info("书籍类型：{}", BookTypeEnum.getName(type));
            queryWrapper.eq("book_type", BookTypeEnum.getName(type));
        }
        //全量书籍
        List<Book> books = bookMapper.selectList(queryWrapper);
        QueryWrapper<Weight> weightQueryWrapper = new QueryWrapper<>();
        //权重
        weightQueryWrapper.eq("deleted", 0);
        Weight weight = weightMapper.selectOne(weightQueryWrapper);
        weight = weight == null ? defaultWeight() : weight;
        List<String> weigthSort = new ArrayList<>();
        for (Book book : books) {
            String key = (book.getBookClickCount() * weight.getClick() + book.getFavoriteCount() * weight.getFavorite() +
                    book.getLikeCount() * weight.getWLike()) + "-" + book.getId();
            weigthSort.add(key);
        }
        weigthSort.sort((o1, o2) -> {
            int o1Int = Integer.parseInt(o1.substring(0, o1.indexOf("-")));
            int o2Int = Integer.parseInt(o2.substring(0, o2.indexOf("-")));
            if (o1Int > o2Int) {
                return -1;
            } else if (o1Int < o2Int) {
                return 1;
            }
            return 0;
        });
        Page<Book> page = new Page<>(pageNum, pageSize);
        int pageStart = Math.min((pageNum - 1) * pageSize, books.size());
        logger.info("pageStart={},pageNum={}", pageStart, pageSize);
        List<String> idStrs = weigthSort.subList(pageStart, pageSize + pageStart);

        logger.info("{}", idStrs);
        List<Integer> bookIds = new ArrayList<>();
        idStrs.forEach(id -> {
            String realId = id.substring(id.indexOf("-") + 1);
            bookIds.add(Integer.parseInt(realId));
        });
        List<Book> bookList = bookMapper.selectBatchIds(bookIds);
        page.setRecords(bookList);
        page.setTotal(books.size());
        page.setSize(bookList.size());
        page.setPages((int) Math.ceil((double) bookList.size() / pageSize));
        return page;
    }

    private Weight defaultWeight() {
        Weight weight = new Weight();
        weight.setClick(1);
        weight.setFavorite(1);
        weight.setWLike(1);
        return weight;
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
        UserFavoriteBookVo userFavoriteBookVo = new UserFavoriteBookVo();
        Book book = bookMapper.selectById(bookId);
        BeanUtils.copyProperties(book, userFavoriteBookVo);
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.select("id", "book_id", "chapter_num", "chapter_name").eq("book_id", bookId);
        List<Chapter> chapters = chapterMapper.selectList(chapterQueryWrapper);
        userFavoriteBookVo.setChapters(chapters);

        User user = userSession.localUser();
        if (user != null) {
            UserFavorite userFavorite = new UserFavorite();
            userFavorite.setUserId(user.getId());
            QueryWrapper<UserFavorite> queryWrapper = new QueryWrapper<>(userFavorite);
            List<UserFavorite> userFavorites = userFavoriteMapper.selectList(queryWrapper);
            List<Integer> books = userFavorites.stream().map(UserFavorite::getBookId).collect(Collectors.toList());
            userFavoriteBookVo.setIsFavorite(books.contains(bookId));
            return userFavoriteBookVo;
        }

        return userFavoriteBookVo;
    }
}
