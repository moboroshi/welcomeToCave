package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.Message;
import com.maboroshi.cave.pojo.PageBean;

import java.util.List;

public interface MessageService {

    PageBean list(Integer page, Integer pageSize);

    void add(Message message);

    void update(Message message);

    void delete(Integer messageId);
}
