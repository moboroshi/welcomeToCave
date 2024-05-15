package com.maboroshi.cave.controller;

import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.Userdata;
import com.maboroshi.cave.pojo.JapaneseWord;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.service.JapaneseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/japanese")
public class JapaneseController {
    @Autowired
    private JapaneseService japaneseService;

    @Log
    @GetMapping("/{userId}")
    public Result list(@PathVariable Integer userId){
        List<JapaneseWord> japaneseWordList=  japaneseService.list(userId);
        return Result.success(japaneseWordList);
    }

    @Log
    @PostMapping
    public Result update(@RequestBody Userdata userdata){
        japaneseService.update(userdata);
        return Result.success();
    }
}
