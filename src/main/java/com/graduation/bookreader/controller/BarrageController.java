package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.Barrage;
import com.graduation.bookreader.model.params.BookUnUpParam;
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
    public Result listBarrage(Integer chapterId, String content, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(barrageService.listBarrage(chapterId, content, pageNum, pageSize));
    }

    @PostMapping("/saveBarrage")
    public Result saveBarrage(@RequestBody Barrage barrage) {
        barrageService.addBarrage(barrage);
        return Result.success();
    }

    /**
     * 后台接口
     *
     * @param level
     * @param name
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/barrages")
    public Result barrages(Integer level, String name, Integer pageIndex, Integer pageSize) {
        return Result.success(barrageService.barrages(level, name, pageIndex, pageSize));
    }

    @PostMapping("/deleteBarrages")
    public Result deleteBarrages(@RequestBody BookUnUpParam bookUnUpParam) {
        barrageService.deleteBarrages(bookUnUpParam);
        return Result.success();
    }

    @PostMapping("/updateBarrage")
    public Result updateBarrage(@RequestBody Barrage barrage) {
        barrageService.updateBarrage(barrage);
        return Result.success();
    }


}
