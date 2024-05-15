package com.maboroshi.cave.service;
import com.maboroshi.cave.pojo.Site;

import java.util.List;


public interface SiteService {
    List<Site> list(String belongTo);
}
