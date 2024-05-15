package com.maboroshi.cave.controller;


import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.pojo.Site;
import com.maboroshi.cave.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private SiteService siteService;
    @Log
    @GetMapping("/{belongTo}")
    public Result list(@PathVariable String belongTo){
        List<Site> site = siteService.list(belongTo);
        return Result.success(site);
    }
}
