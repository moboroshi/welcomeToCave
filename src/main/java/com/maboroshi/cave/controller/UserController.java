package com.maboroshi.cave.controller;

import com.alibaba.fastjson.JSONObject;
import com.maboroshi.cave.anno.Log;
import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.pojo.User;
import com.maboroshi.cave.service.UserService;
import com.maboroshi.cave.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Log
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User user1 = userService.login(user);
        if (user1 != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("userId", user1.getUserId());

            String jwt = JWTUtils.genJWT(claims);
            JSONObject returnObject = new JSONObject();
            returnObject.put("userInfo", user1);
            returnObject.put("token", jwt);
            return Result.success(returnObject);
        }else {
            return Result.error("用户名或密码错误");
        }
    }

    @Log
    @PostMapping
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    @Log
    @PutMapping
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @Log
    @GetMapping("/{userId}")
    public Result getUser(@PathVariable Integer userId){
        User user =  userService.getUser(userId);
        return Result.success(user);
    }
}
