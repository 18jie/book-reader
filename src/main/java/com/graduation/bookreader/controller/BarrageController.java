package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.service.BarrageService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2020-11-01
 * Time: 21:20
 */
@RestController
@RequestMapping("/barrage")
public class BarrageController {

    @Resource
    private BarrageService barrageService;

    @GetMapping("/listBarrage")
    public Result listBarrage(Integer bookId, Integer chapterId, String contentCode, Integer pageNum, Integer pageSize) {
        return Result.success(barrageService.listBarrage(bookId, chapterId, contentCode, pageNum, pageSize));
    }

    @PostMapping("/saveBarrage")
    public Result saveBarrage(@RequestBody Barrage barrage){
        barrageService.addBarrage(barrage);
        return Result.success();
    }



}
