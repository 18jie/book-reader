package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * 权重表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:19
 */
@TableName("b_weight")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Weight extends BaseModel implements Serializable {

    private Integer click;

    private Integer favorite;

    private Integer like;

}
