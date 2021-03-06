package com.graduation.bookreader.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-05
 * Time: 23:29
 */
@Data
@ToString
public class BookVo {

    private String _id;

    private String title;

    private String author;

    private String cover;

    private Boolean isSerial;

    private String majorCate;

    private String minorCate;

    private Integer latelyFollower;

    private String shortIntro;

    private String longIntro;

    private Integer introLimit;

}
