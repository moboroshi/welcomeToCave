package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JapaneseWord {
    private Integer wordId;
    private String japanese;
    private String chinese;
    private Integer viewed;
    private Boolean click = false;
}
