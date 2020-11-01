package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.bookreader.model.Chapter;
import com.graduation.bookreader.repo.ChapterMapper;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:13
 */
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public IPage<Chapter> listChapterbyBookId(Integer bookId, Integer pageNum, Integer pageSize) {
        Page<Chapter> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "book_id", "chapter_name").eq("book_id", bookId);
        return chapterMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Chapter getChapterById(Integer id) {
        return chapterMapper.selectById(id);
    }
}
