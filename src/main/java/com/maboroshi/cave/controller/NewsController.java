package com.maboroshi.cave.controller;


import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.pojo.News;
import com.maboroshi.cave.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Log
    @GetMapping("/bbc")
    public Result listBBC(){
        List<News> newsList = newsService.listBBC();
        return Result.success(newsList);
    }
    @Log
    @GetMapping("/bhmt")
    public Result listBHMT(){
        List<News> newsList = newsService.listBHMT();
        return Result.success(newsList);
    }

}
