package com.maboroshi.cave.service.impl;

import com.github.pagehelper.Page;
import com.maboroshi.cave.mapper.MessageMapper;
import com.maboroshi.cave.pojo.Message;
import com.maboroshi.cave.pojo.PageBean;
import com.maboroshi.cave.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.util.List;

@Service
public class MessageServiceimpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public PageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Message> messageList = messageMapper.list();
        Page<Message> messagePage = (Page<Message>) messageList;
        PageBean pageBean = new PageBean(messagePage.getTotal(), messagePage.getResult());
        return pageBean;
    }

    @Override
    public void add(Message message) {
        messageMapper.add(message);
    }

    @Override
    public void update(Message message) {
        messageMapper.update(message);
    }

    @Override
    public void delete(Integer messageId) {
        messageMapper.dalete(messageId);
    }
}
