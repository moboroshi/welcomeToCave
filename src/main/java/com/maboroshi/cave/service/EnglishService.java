package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.EnglishWord;
import com.maboroshi.cave.pojo.Userdata;

import java.util.List;

public interface EnglishService {
    List<EnglishWord> list(Integer userId);

    void update(Userdata userdata);
}
