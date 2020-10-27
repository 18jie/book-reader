package com.graduation.bookreader.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-25
 * Time: 19:05
 */
@Data
@ToString
@EqualsAndHashCode
public class BaseModel {

//    @TableId(type = IdType.AUTO)
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

}
