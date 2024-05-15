package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private Integer replyId;
    private Integer messageId;
    private Integer userId;
    private LocalDate date;
    private String text;
    private LocalDateTime replyTime;
}
