package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * 用户表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:03
 */
@TableName("b_user")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel implements Serializable {

    private String userName;

    private String userPhone;

    // TODO 新增字段，需要添加到数据库
    private Integer age;

    private String password;

}
