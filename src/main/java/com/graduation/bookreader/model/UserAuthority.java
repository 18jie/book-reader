package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * 用户权限表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:18
 */
@TableName("b_user_authority")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserAuthority extends BaseModel implements Serializable {

    private Integer userId;

    private Integer writeAuthority;

    private Integer readAuthority;

}
