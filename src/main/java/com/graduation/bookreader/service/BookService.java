package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-29
 * Time: 17:15
 */
public interface BookService {

    /**
     * 通过类型查询书籍，已经通过权重排序
     * 分三部查，1.查所有书籍id，点击，喜爱。查询权重。确定排序id，按照id查询
     *
     * @param type     书籍类型
     * @param pageNum  页码
     * @param pageSize 单页大小
     * @return
     */
    IPage<Book> listBookByType(Integer type, Integer pageNum, Integer pageSize);

    /**
     * 通过名称查询书籍
     *
     * @param name     书名
     * @param pageNum  页码
     * @param pageSize 单页大小
     * @return
     */
    IPage<Book> listBookByName(String name, Integer pageNum, Integer pageSize);

    /**
     * TODO 这个是查询的详情页，后期需要修改
     * 需要增加用户是否收藏，是否喜欢等等信息
     *
     * @param id
     * @return
     */
    Book getBookById(Integer id);

    /**
     * 书籍详情，如果用户已经登录，显示是否收藏
     * @param bookId
     * @return
     */
    UserFavoriteBookVo bookDetail(Integer bookId);

}
