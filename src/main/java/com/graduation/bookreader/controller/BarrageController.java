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
    public Result listBarrage(Integer chapterId, String comment, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(barrageService.listBarrage(chapterId, comment, pageNum, pageSize));
    }

    @PostMapping("/saveBarrage")
    public Result saveBarrage(@RequestBody Barrage barrage){
        barrageService.addBarrage(barrage);
        return Result.success();
    }



}
