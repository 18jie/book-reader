package com.graduation.bookreader.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-02
 * Time: 22:26
 */
@Data
@ToString(callSuper = true)
public class DashboardVo {

    private Integer msgCount;

    private Integer bookCount;

}
