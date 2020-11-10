package com.graduation.bookreader.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bookreader.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 21:51
 */
public interface UserMapper extends BaseMapper<User> {
}
