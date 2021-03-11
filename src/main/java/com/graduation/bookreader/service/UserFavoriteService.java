package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.params.QueryParam;
import com.graduation.bookreader.model.vo.BookVo;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-22
 * Time: 20:15
 */
public interface UserFavoriteService {

    List<Book> list();

    boolean addOrRemoveFavorite(Integer bookId);

    IPage<UserFavoriteBookVo> userFavorite(QueryParam queryParam);

    IPage<BookVo> listFavoriteBookVo(Integer pageNum);

    void deleteFavorites(BookUnUpParam bookUnUpParam);

    boolean isFavorite(Integer bookId);

}
