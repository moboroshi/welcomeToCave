package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.PageBean;
import com.maboroshi.cave.pojo.Reply;

import java.util.List;

public interface ReplyService {
    PageBean list(Integer messageId, Integer page, Integer pageSize);

    void delete(Integer replyId);

    void add(Reply reply);
}
