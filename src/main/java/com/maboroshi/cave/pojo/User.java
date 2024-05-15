package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String nickname;
    private String password;
    private String userImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
