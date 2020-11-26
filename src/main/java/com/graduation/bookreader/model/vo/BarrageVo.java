package com.graduation.bookreader.model.vo;

import com.graduation.bookreader.model.Barrage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-26
 * Time: 22:48
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BarrageVo extends Barrage {

    private String userName;

}
