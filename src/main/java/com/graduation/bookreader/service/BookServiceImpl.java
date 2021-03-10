package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.enums.BookTypeEnum;
import com.graduation.bookreader.model.*;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.vo.BookDetailVo;
import com.graduation.bookreader.model.vo.BookVo;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;
import com.graduation.bookreader.repo.BookMapper;
import com.graduation.bookreader.repo.ChapterMapper;
import com.graduation.bookreader.repo.UserFavoriteMapper;
import com.graduation.bookreader.repo.WeightMapper;
import com.graduation.bookreader.util.UserSession;
import org.apache.commons.lang.StringUtils;
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
    public IPage<Book> listBookByType(Integer type, String name, Integer pageNum, Integer pageSize) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        Page<Book> page = new Page<>(pageNum, pageSize);
        queryWrapper.eq("deleted", 0);
        if (Objects.nonNull(type)) {
            logger.info("书籍类型：{}", BookTypeEnum.getName(type));
            queryWrapper.eq("book_type", BookTypeEnum.getName(type));
        }
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.eq("book_name", name);
        }
        //全量书籍
        Page<Book> page1 = bookMapper.selectPage(page, queryWrapper);
//        QueryWrapper<Weight> weightQueryWrapper = new QueryWrapper<>();
        //权重
//        weightQueryWrapper.eq("deleted", 0);
//        Weight weight = weightMapper.selectOne(weightQueryWrapper);
//        weight = weight == null ? defaultWeight() : weight;
//        List<String> weigthSort = new ArrayList<>();
//        for (Book book : books) {
//            String key = (book.getBookClickCount() * weight.getClick() + book.getFavoriteCount() * weight.getFavorite() +
//                    book.getLikeCount() * weight.getWLike()) + "-" + book.getId();
//            weigthSort.add(key);
//        }
//        weigthSort.sort((o1, o2) -> {
//            int o1Int = Integer.parseInt(o1.substring(0, o1.indexOf("-")));
//            int o2Int = Integer.parseInt(o2.substring(0, o2.indexOf("-")));
//            if (o1Int > o2Int) {
//                return -1;
//            } else if (o1Int < o2Int) {
//                return 1;
//            }
//            return 0;
//        });
//        logger.info("pageNum={},pageSize={}", pageNum, pageSize);
//        Page<Book> page = new Page<>(pageNum, pageSize);
//        int pageStart = Math.min((pageNum - 1) * pageSize, books.size());
//        logger.info("pageStart={},pageNum={}", pageStart, pageSize);
//        List<String> idStrs = weigthSort.subList(pageStart, pageSize + pageStart);
//
//        logger.info("{}", idStrs);
//        List<Integer> bookIds = new ArrayList<>();
//        idStrs.forEach(id -> {
//            String realId = id.substring(id.indexOf("-") + 1);
//            bookIds.add(Integer.parseInt(realId));
//        });
//        List<Book> bookList = bookMapper.selectBatchIds(bookIds);
//        page.setRecords(bookList);
//        page.setTotal(books.size());
//        page.setSize(bookList.size());
//        page.setPages((int) Math.ceil((double) bookList.size() / pageSize));
        return page1;
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
            userFavorite.setDeleted(0);
            QueryWrapper<UserFavorite> queryWrapper = new QueryWrapper<>(userFavorite);
            List<UserFavorite> userFavorites = userFavoriteMapper.selectList(queryWrapper);
            List<Integer> books = userFavorites.stream().map(UserFavorite::getBookId).collect(Collectors.toList());
            userFavoriteBookVo.setIsFavorite(books.contains(bookId));
            return userFavoriteBookVo;
        }

        return userFavoriteBookVo;
    }

    @Override
    public void countByType() {
        // TODO
        List<Map<Integer, Integer>> maps = bookMapper.bookCountByType();
    }

    @Override
    public List<BookVo> hotBooks(Integer type) {
        List<Book> bookList;
        if (type == 1) {
            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("deleted", 0).orderByDesc("book_click_count").last("limit 4");
            bookList = bookMapper.selectList(queryWrapper);
        } else if (type == 2) {
            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("deleted", 0).orderByDesc("book_update_time").last("limit 4");
            bookList = bookMapper.selectList(queryWrapper);
        } else {
            QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("deleted", 0).orderByDesc("book_click_count");
            bookList = bookMapper.selectList(queryWrapper);
        }
        List<Integer> ids = bookList.stream().map(Book::getId).collect(Collectors.toList());
        Map<Integer, String> chaptersMap = this.getChaptersByBookIds(ids);
        List<BookVo> bookVos = new ArrayList<>();
        bookList.forEach(book -> {
            String content = chaptersMap.get(book.getId());
            bookVos.add(this.buildBookVo(book, content));
        });
        return bookVos;
    }

    @Override
    public BookVo bookVoById(Integer id) {
        Book book = bookMapper.selectById(id);
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", id).orderByAsc("id").last("limit 1");
        Chapter chapter = chapterMapper.selectOne(queryWrapper);
        return this.buildBookVo(book, chapter.getContent());
    }

    @Override
    public BookDetailVo bookDetailVo(Integer id) {
        BookVo bookVo = this.bookVoById(id);
        Book book = bookMapper.selectById(id);
        BookDetailVo bookDetailVo = new BookDetailVo();
        BeanUtils.copyProperties(bookVo, bookDetailVo);
        bookDetailVo.setRetentionRatio(80);

        QueryWrapper<Chapter> query = new QueryWrapper<>();
        query.eq("deleted", 0).eq("up_status", 0).eq("book_id", id).orderByDesc("id").last("limit 1");
        Chapter chapter = chapterMapper.selectOne(query);
        bookDetailVo.setSerializeWordCount(chapter.getContent().toCharArray().length);
        bookDetailVo.setUpdated(book.getBookUpdateTime().getTime());
        bookDetailVo.setLastChapter(chapter.getChapterName());
        return bookDetailVo;
    }

    @Override
    public List<Book> searchBookByName(String name) {
        QueryWrapper<Book> bookQueryWrapper = new QueryWrapper<>();
        bookQueryWrapper.eq("deleted", 0).eq("up_status", 0).like("book_name", name);
        List<Book> bookList = bookMapper.selectList(bookQueryWrapper);
        return bookList;
    }

    @Override
    public Page<BookVo> bookVosByPage(Integer type, Integer pageNum) {
        Page<Book> page = new Page<>(pageNum, 8);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if (type == 0) {
            queryWrapper.select("id").eq("up_status", 0).eq("deleted", 0).orderByDesc("book_click_count");
        } else {
            queryWrapper.select("id").eq("upStatus", 0).eq("deleted", 0).eq("book_type", BookTypeEnum.getName(type)).orderByDesc("book_click_count");
        }
        Page<Book> page1 = bookMapper.selectPage(page, queryWrapper);

        Page<BookVo> bookVoPage = new Page<>(pageNum, 8);
        bookVoPage.setTotal(page1.getTotal());
        bookVoPage.setSize(page1.getSize());
        bookVoPage.setPages(page1.getPages());

        List<BookVo> bookVos = new ArrayList<>();
        page1.getRecords().forEach(book -> bookVos.add(this.bookVoById(book.getId())));
        bookVoPage.setRecords(bookVos);
        return bookVoPage;
    }

    @Override
    public List<Map<String, Object>> getBookTypes() {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("book_type").groupBy("book_type");
        List<Book> bookList = bookMapper.selectList(queryWrapper);
        List<Map<String, Object>> mapList = new ArrayList<>();
//        bookList.forEach(book -> {
//            Map<String, Object> map = new HashMap<>();
//            map.put("title", book.getBookType());
//            map.put("_id", BookTypeEnum.getType(book.getBookType()));
//            mapList.add(map);
//        });
        for (BookTypeEnum value : BookTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", value.getName());
            map.put("_id", value.getType());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void unUpBookByIds(BookUnUpParam bookUnUpParam) {
        List<Integer> ids = bookUnUpParam.getIds();
        ids.forEach(id -> {
            Book book = new Book();
            book.setId(id);
            book.setUpStatus(bookUnUpParam.getType());
            bookMapper.updateById(book);
        });
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateById(book);
    }

    private Map<Integer, String> getChaptersByBookIds(List<Integer> bookIds) {
        Map<Integer, String> chapterMap = new HashMap<>();
        bookIds.forEach(id -> {
            QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
            chapterQueryWrapper.eq("book_id", id).orderByAsc("id").last("limit 1");
            Chapter chapter = chapterMapper.selectOne(chapterQueryWrapper);
            chapterMap.put(id, chapter.getContent());
        });
        return chapterMap;
    }

    private BookVo buildBookVo(Book book, String intro) {
        BookVo bookVo = new BookVo();
        bookVo.set_id(String.valueOf(book.getId()));
        bookVo.setAuthor(book.getBookWriter());
        bookVo.setCover(book.getImgPath());
        bookVo.setIsSerial(true);
        bookVo.setLatelyFollower(book.getBookClickCount());
        bookVo.setShortIntro(intro);
        bookVo.setLongIntro(intro);
        bookVo.setIntroLimit(60);
        bookVo.setTitle(book.getBookName());
        return bookVo;
    }

}
