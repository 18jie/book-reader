package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.model.dto.BarrageDto;
import com.graduation.bookreader.model.params.BookUnUpParam;
import com.graduation.bookreader.model.vo.BarrageVo;
import com.graduation.bookreader.model.vo.CommentVo;

import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:20
 */
public interface BarrageService {

    IPage<CommentVo> listBarrage(Integer chapterId, String comment, Integer pageNum, Integer pageSize);

    void addBarrage(Barrage barrage);

    IPage<BarrageDto> barrages(Integer level, String name,Integer pageIndex,Integer pageSize);

    void deleteBarrages(BookUnUpParam bookUnUpParam);

    void updateBarrage(Barrage barrage);

}
