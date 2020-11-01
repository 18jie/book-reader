package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.Chapter;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:09
 */
public interface ChapterService {

    /**
     * 通过书籍id查询章节
     *
     * @param bookId
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Chapter> listChapterbyBookId(Integer bookId, Integer pageNum, Integer pageSize);

    Chapter getChapterById(Integer id);

}
