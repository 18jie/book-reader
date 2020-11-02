package com.graduation.bookreader.model.vo;

import com.graduation.bookreader.model.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

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

    private Boolean isFavorite;

}
