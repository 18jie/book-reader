package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

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

    private String username;

    private String phone;

    private Integer age;

    private String password;

    private Date loginTime;

    private Integer loginCount;

    private Integer auth;

}
