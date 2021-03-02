package com.graduation.bookreader.controller;

import com.graduation.bookreader.model.vo.DashboardVo;
import com.graduation.bookreader.service.MessageService;
import com.graduation.bookreader.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * <p>
 * Author: 丰杰
 * Date: 2021-03-02
 * Time: 22:27
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping("/dashboard")
    public Result<DashboardVo> buildDashBoard(){
        return Result.success(messageService.buildDashboard());
    }

}
