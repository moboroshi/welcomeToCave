package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishWord {
    private Integer wordId;
    private String english;
    private String pronunciation;
    private String chinese;
    private Integer viewed;
    private Boolean click = false;
}
