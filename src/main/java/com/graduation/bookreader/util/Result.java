package com.graduation.bookreader.util;

import lombok.Data;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 20:29
 */
@Data
public class Result<T> {

    private Integer code = 0;

    private String msg;

    private T data;

    public static <T> Result success(){
        Result<T> result = new Result<>();
        result.msg = "";
        return result;
    }

    public static <T> Result success(T data){
        Result<T> result = new Result<>();
        result.msg = "";
        result.data = data;
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.msg = msg;
        result.code = 1;
        return result;
    }

}
