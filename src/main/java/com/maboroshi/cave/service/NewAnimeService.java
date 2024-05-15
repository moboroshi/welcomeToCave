package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.anime;

import java.util.List;

public interface NewAnimeService {
    List<anime> list(Integer season);

    List<anime> listByDay(Integer season, Integer day);
}
