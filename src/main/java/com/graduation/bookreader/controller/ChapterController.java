package com.graduation.bookreader.controller;

import com.graduation.bookreader.service.ChapterService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.*;

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
    public Result listChapterByBookId(Integer bookId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(chapterService.listChapterbyBookId(bookId, pageNum, pageSize));
    }

    @GetMapping("/getChapterById")
    public Result getChapterById(Integer id) {
        return Result.success(chapterService.getChapterById(id));
    }

    @GetMapping("/chapterDetail")
    public Result chapterDetail(Integer chapterId) {
        return Result.success(chapterService.chapterDetail(chapterId));
    }

    @GetMapping("/chaptersByBookId")
    public Result chaptersByBookId(Integer bookId) {
        return Result.success(chapterService.chapterVosByBookId(bookId));
    }

    @GetMapping("/chapterContent/{chapterId}")
    public Result chapterContent(@PathVariable("chapterId") Integer chapterId) {
        return Result.success(chapterService.chapterContentVoByChapterId(chapterId));
    }

}
