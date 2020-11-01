package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.repo.BarrageMapper;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:22
 */
public class BarrageServiceImpl implements BarrageService {

    @Resource
    private BarrageMapper barrageMapper;

    @Override
    public IPage<Barrage> listBarrage(Integer bookId, Integer chapterId, String contentCode, Integer pageNum, Integer pageSize) {
        Page<Barrage> page = new Page<>(pageNum, pageSize);
        Barrage barrage = new Barrage();
        barrage.setBookId(bookId);
        barrage.setChapterId(chapterId);
        barrage.setContentCode(contentCode);
        QueryWrapper<Barrage> queryWrapper = new QueryWrapper<>(barrage);
        return barrageMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void addBarrage(Barrage barrage) {
        // TODO 看是前端计算MD5还是后端计算
        barrage.setCreateTime(new Date());
        barrage.setUpdateTime(new Date());
        barrageMapper.insert(barrage);
    }
}
