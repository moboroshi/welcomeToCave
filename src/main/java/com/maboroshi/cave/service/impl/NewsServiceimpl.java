package com.maboroshi.cave.service.impl;

import com.maboroshi.cave.mapper.NewsMapper;
import com.maboroshi.cave.pojo.News;
import com.maboroshi.cave.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceimpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<News> listBBC() {
        return newsMapper.listBBC();
    }

    @Override
    public void insertBBC(News news) {
        newsMapper.insertBBC(news);
    }

    @Override
    public List<News> listBHMT() {
        return newsMapper.listBHMT();
    }

    @Override
    public void insertBHMT(News gameNews) {
        newsMapper.insertBHMT();
    }
}
