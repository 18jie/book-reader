package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.Barrage;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:20
 */
public interface BarrageService {

    IPage<Barrage> listBarrage(Integer chapterId, String comment, Integer pageNum, Integer pageSize);

    void addBarrage(Barrage barrage);

}
