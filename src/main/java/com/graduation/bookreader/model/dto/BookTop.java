package com.graduation.bookreader.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-14
 * Time: 16:28
 */
@Data
@ToString
@EqualsAndHashCode
public class BookTop {

    private String bookType;

    private Double counts;

}
