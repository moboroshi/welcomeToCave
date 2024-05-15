package com.maboroshi.cave.controller;

import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.EnglishWord;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.pojo.Userdata;
import com.maboroshi.cave.service.EnglishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/english")
public class EnglishController {
    @Autowired
    private EnglishService englishService;

    @Log
    @GetMapping("/{userId}")
    public Result list(Integer userId){
        List<EnglishWord> englishWordList= englishService.list(userId);
        return Result.success(englishWordList);
    }

    @Log
    @PostMapping
    public Result update(@RequestBody Userdata userdata){
        englishService.update(userdata);
        return Result.success();
    }
}
