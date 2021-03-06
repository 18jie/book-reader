package com.graduation.bookreader.model.vo;

import lombok.Data;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-06
 * Time: 15:36
 */
@Data
public class ChapterVo {

    private Integer id;

    private String bookName;

    private Integer order;

    private String title;

    /**
     * 赋id的值
     */
    private Integer link;

    private Boolean isVip;

}
