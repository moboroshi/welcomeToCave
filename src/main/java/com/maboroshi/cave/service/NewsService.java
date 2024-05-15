package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.News;

import java.util.List;

public interface NewsService {
    List<News> listBBC();

    void insertBBC(News news);

    List<News> listBHMT();

    void insertBHMT(News gameNews);

}
