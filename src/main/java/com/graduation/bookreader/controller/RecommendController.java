package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.Recommend;
import com.graduation.bookreader.service.RecommendService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-04
 * Time: 22:48
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    private RecommendService recommendService;


    @GetMapping("/list")
    public Result list(){
        return Result.success(recommendService.list());
    }

}
