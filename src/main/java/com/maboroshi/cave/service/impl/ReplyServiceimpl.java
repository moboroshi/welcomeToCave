package com.maboroshi.cave.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.maboroshi.cave.mapper.ReplyMapper;
import com.maboroshi.cave.pojo.Message;
import com.maboroshi.cave.pojo.PageBean;
import com.maboroshi.cave.pojo.Reply;
import com.maboroshi.cave.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceimpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Override
    public PageBean list(Integer messageId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Reply> replyList = replyMapper.list(messageId);
        Page<Reply> replyePage = (Page<Reply>) replyList;
        PageBean pageBean = new PageBean(replyePage.getTotal(), replyePage.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer replyId) {
        replyMapper.delete(replyId);
    }

    @Override
    public void add(Reply reply) {
        replyMapper.add(reply);
    }
}
