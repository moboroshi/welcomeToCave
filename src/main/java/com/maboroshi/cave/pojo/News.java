package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private LocalDate date;
    private String title;
    private String image;
    private String link;
}
