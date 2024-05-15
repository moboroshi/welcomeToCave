package com.maboroshi.cave.service.impl;

import com.maboroshi.cave.mapper.JapaneseMapper;
import com.maboroshi.cave.pojo.Userdata;
import com.maboroshi.cave.pojo.JapaneseWord;
import com.maboroshi.cave.service.JapaneseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class JapaneseServiceimpl implements JapaneseService {
    @Autowired
    private JapaneseMapper japaneseMapper;
    @Override
    public List<JapaneseWord> list(Integer userId) {
        List<JapaneseWord> japaneseWordList = japaneseMapper.list();
        List<Userdata> userdataList = japaneseMapper.listData(userId);
        System.out.println(userdataList);
        Iterator<JapaneseWord> iterator = japaneseWordList.iterator();
        if (userdataList.size()!=0){
            Integer Count = japaneseMapper.getSum(userId) == null?0:japaneseMapper.getSum(userId);
            Integer averageCount = Count/12717;
            while (iterator.hasNext()) {
                JapaneseWord japaneseWord = iterator.next();
                for (Userdata userdata : userdataList) {
                    if (userdata.getWordId().equals(japaneseWord.getWordId())) {
                        japaneseWord.setClick(userdata.getClick()); // 更新 click 属性
                        japaneseWord.setViewed(userdata.getViewed());
                        if (userdata.getViewed() > averageCount) {
                            // 如果浏览次数大于平均值，移除当前单词，并终止内部循环
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }

        return japaneseWordList;
    }

    @Override
    public void update(Userdata userdata) {
        japaneseMapper.update(userdata);
    }
}
