package com.graduation.bookreader.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-06
 * Time: 15:50
 */
@Data
@ToString
public class ContentVo {

    private List<LineVo> content;

    private String chapterTitle;

    private Integer order;

    private Boolean isVip;

}
