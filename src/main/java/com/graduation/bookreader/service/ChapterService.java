package com.graduation.bookreader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduation.bookreader.model.Chapter;
import com.graduation.bookreader.model.vo.ChapterDetailVo;
import com.graduation.bookreader.model.vo.ChapterVo;
import com.graduation.bookreader.model.vo.ContentVo;

import java.util.List;

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

    ChapterDetailVo chapterDetail(Integer chapterId);

    /**
     * 通过书籍id查询章节vo
     * @param bookId 书籍id
     * @return
     */
    List<ChapterVo> chapterVosByBookId(Integer bookId);

    /**
     * 通过章节id获取章节内容
     * @param chapterId 章节id
     * @return 章节内容
     */
    ContentVo chapterContentVoByChapterId(Integer chapterId);

}
