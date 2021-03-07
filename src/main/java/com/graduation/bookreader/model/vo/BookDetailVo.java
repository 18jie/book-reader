package com.graduation.bookreader.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-07
 * Time: 15:00
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookDetailVo extends BookVo{

    private Integer retentionRatio;

    private Integer serializeWordCount;

    private Long updated;

    private String lastChapter;

}
