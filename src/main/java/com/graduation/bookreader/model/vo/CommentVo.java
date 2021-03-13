package com.graduation.bookreader.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-13
 * Time: 21:52
 */
@Data
@ToString
public class CommentVo {

    private Integer id;

    private CommentUser commentUser;

    private String content;

    private String createDate;

    private List<CommentVo> childrenList;

}
