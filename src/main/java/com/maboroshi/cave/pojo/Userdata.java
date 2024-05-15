package com.maboroshi.cave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdata {
        private Integer wordId;
        private String userId;
        private Integer viewed;
        private Boolean click;
}
