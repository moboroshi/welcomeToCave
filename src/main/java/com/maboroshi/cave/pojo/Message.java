package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer messageId;
    private Integer userId;
    private Integer count;
    private LocalDate date;
    private String text;
    private LocalDateTime commentTime;
}
