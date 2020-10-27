package com.graduation.bookreader.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.graduation.bookreader.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:
 * 用户收藏表
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:16
 */
@TableName("b_user_favorite")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserFavorite extends BaseModel implements Serializable {

    private Integer userId;

    private Integer bookId;

}
