package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:08
 */
@TableName("b_administrator")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Administrator extends BaseModel implements Serializable {

    private String userName;

    private String userPhone;

    private String password;

}
