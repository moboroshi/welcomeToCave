package com.maboroshi.cave.controller;

import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.Message;
import com.maboroshi.cave.pojo.PageBean;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Log
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = messageService.list(page,pageSize);
        return Result.success(pageBean);
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Message message){
        messageService.add(message);
        return Result.success();
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Message message){
        messageService.update(message);
        return Result.success();
    }
    @Log
    @DeleteMapping("/{messageId}")
    public Result delete(@PathVariable Integer messageId){
        messageService.delete(messageId);
        return Result.success();
    }
}
