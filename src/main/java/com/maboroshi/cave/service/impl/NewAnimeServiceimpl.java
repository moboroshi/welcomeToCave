package com.maboroshi.cave.service.impl;

import com.maboroshi.cave.mapper.NewAnimeMapper;
import com.maboroshi.cave.pojo.anime;
import com.maboroshi.cave.service.NewAnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewAnimeServiceimpl implements NewAnimeService {
    @Autowired
    private NewAnimeMapper newAnimeMapper;
    @Override
    public List<anime> list(Integer season) {
        return newAnimeMapper.list(season);
    }

    @Override
    public List<anime> listByDay(Integer season, Integer day) {
        return newAnimeMapper.listByDay(season,day);
    }
}
