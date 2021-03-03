package com.graduation.bookreader.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bookreader.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 21:54
 */
public interface BookMapper extends BaseMapper<Book> {

    @Select("select type,count(1) from b_book where deleted = 0")
    List<Map<Integer,Integer>> bookCountByType();

}
