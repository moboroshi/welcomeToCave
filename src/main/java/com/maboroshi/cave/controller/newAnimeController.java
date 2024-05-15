package com.maboroshi.cave.controller;

import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.pojo.anime;
import com.maboroshi.cave.service.NewAnimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/anime")
public class newAnimeController {
    @Autowired
    private NewAnimeService newAnimeService;
    @Log
    @GetMapping("/{season}")
    public Result list(@PathVariable Integer season){
        List<anime> animes = newAnimeService.list(season);
        return Result.success(animes);
    }
    @Log
    @GetMapping("/{season}/{day}")
    public Result list(@PathVariable("season") Integer season ,@PathVariable("day") Integer day){
        List<anime> animes = newAnimeService.listByDay(season,day);
        return Result.success(animes);
    }
}
