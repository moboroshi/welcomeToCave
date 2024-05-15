package com.maboroshi.cave.controller;

import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.PageBean;
import com.maboroshi.cave.pojo.Reply;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Log
    @GetMapping("/{messageId}")
    public Result list(@PathVariable Integer messageId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean =  replyService.list(messageId,page,pageSize);
        return Result.success(pageBean);
    }
    @Log
    @DeleteMapping("/{replyId}")
    public Result delete(@PathVariable Integer replyId){
        replyService.delete(replyId);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Reply reply){
        replyService.add(reply);
        return Result.success();
    }
}
