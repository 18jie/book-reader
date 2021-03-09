package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * 书籍表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:09
 */
@TableName("b_book")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseModel implements Serializable {

    private String bookName;

    private String bookType;

    private String bookImgUrl;

    private String bookWriter;

    private String bookIntroduction;

    private Date bookUpdateTime;

    private Integer bookClickCount;

    private Integer favoriteCount;

    private Integer likeCount;

    private String bookPath;

    private String imgPath;

    /**
     * 上架状态 0-上 1-下
     */
    private Integer upStatus;

}
