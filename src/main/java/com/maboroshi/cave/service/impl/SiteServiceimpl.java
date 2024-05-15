package com.maboroshi.cave.service.impl;


import com.maboroshi.cave.mapper.SiteMapper;
import com.maboroshi.cave.pojo.Site;
import com.maboroshi.cave.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceimpl implements SiteService {
    @Autowired
    private SiteMapper siteMapper;

    @Override
    public List<Site> list(String belongTo) {
        return siteMapper.list(belongTo);
    }

}
