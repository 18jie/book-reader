package com.graduation.bookreader.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.UserFavorite;
import com.graduation.bookreader.model.vo.UserFavoriteBookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 21:55
 */
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

    IPage<UserFavoriteBookVo> listFavorite(Page<UserFavoriteBookVo> page,@Param("userId") Integer userId,@Param("name") String name);

}
