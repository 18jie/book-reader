package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-04
 * Time: 22:43
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("b_recommend")
public class Recommend extends BaseModel {

    private String link;

    private String img;

}
