package com.graduation.bookreader.model.vo;

import com.graduation.bookreader.model.Book;
import com.graduation.bookreader.model.Chapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-02
 * Time: 23:11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserFavoriteBookVo extends Book implements Serializable {

    private Integer favoriteId;

    private Boolean isFavorite;

    List<Chapter> chapters;

}
