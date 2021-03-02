package com.graduation.bookreader.service;

import com.graduation.bookreader.model.Book;

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

}
