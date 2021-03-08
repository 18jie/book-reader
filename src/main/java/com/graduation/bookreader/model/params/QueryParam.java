package com.graduation.bookreader.model.params;

import lombok.Data;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-08
 * Time: 22:34
 */
@Data
@ToString
public class QueryParam {

    private Integer type;

    private String name;

    private Integer pageIndex;

    private Integer pageSize;

}
