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
 * Date: 2021-03-02
 * Time: 22:22
 */
@TableName("b_user")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Message extends BaseModel {

    private Integer userId;

    private String msg;

    private Integer status;

}
