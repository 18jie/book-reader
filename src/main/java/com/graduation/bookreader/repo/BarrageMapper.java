package com.graduation.bookreader.repo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.model.dto.BarrageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-10-27
 * Time: 21:54
 */
public interface BarrageMapper extends BaseMapper<Barrage> {

    IPage<BarrageDto> barrages(Page<BarrageDto> page, @Param("level") Integer level, @Param("name") String name,@Param("userId") Integer userId);

}
