package com.graduation.bookreader.model.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-09
 * Time: 14:09
 */
@Data
@ToString
public class BarrageDto {

    private Integer id;

    private String username;

    private String content;

    private Integer level;

    private String bookName;

    private String chapterName;

    private Timestamp createTime;

}
