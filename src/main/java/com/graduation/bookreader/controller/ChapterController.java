package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.Chapter;
import com.graduation.bookreader.service.ChapterService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:17
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @GetMapping("/listChapterByBookId")
    public Result listChapterByBookId(Integer bookId, Integer pageNum, Integer pageSize) {
        return Result.success(chapterService.listChapterbyBookId(bookId, pageNum, pageSize));
    }

    public Result getChapterById(Integer id) {
        return Result.success(chapterService.getChapterById(id));
    }

}
