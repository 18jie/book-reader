package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * 弹幕表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:14
 */
@TableName("b_barrage")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Barrage extends BaseModel implements Serializable {

    private Integer bookId;

    private Integer chapterId;

    private String content;

    private String contentCode;

    private Integer level;

}
