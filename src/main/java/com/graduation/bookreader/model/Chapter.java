package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * 章节表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:13
 */
@TableName("b_chapter")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Chapter extends BaseModel implements Serializable {

    private Integer bookId;

    private Integer chapter;

    private String content;

}
