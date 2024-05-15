package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.Userdata;
import com.maboroshi.cave.pojo.JapaneseWord;

import java.util.List;

public interface JapaneseService {
    List<JapaneseWord> list(Integer userId);

    void update(Userdata userdata);
}
