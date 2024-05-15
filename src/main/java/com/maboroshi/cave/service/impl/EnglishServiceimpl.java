package com.maboroshi.cave.service.impl;

import com.maboroshi.cave.mapper.EnglishMapper;
import com.maboroshi.cave.pojo.EnglishWord;
import com.maboroshi.cave.pojo.Userdata;
import com.maboroshi.cave.service.EnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class EnglishServiceimpl implements EnglishService {

    @Autowired
    private EnglishMapper englishMapper;
    @Override
    public List<EnglishWord> list(Integer userId) {
        List<EnglishWord> englishWordList = englishMapper.list();
        List<Userdata> userdataList = englishMapper.listData(userId);
        System.out.println(userdataList);
        Iterator<EnglishWord> iterator = englishWordList.iterator();
        if (userdataList.size()!=0){
            Integer Count = englishMapper.getSum(userId) == null?0:englishMapper.getSum(userId);
            Integer averageCount = Count/12717;
            while (iterator.hasNext()) {
                EnglishWord englishWord = iterator.next();
                for (Userdata userdata : userdataList) {
                    if (userdata.getWordId().equals(englishWord.getWordId())) {
                        englishWord.setClick(userdata.getClick()); // 更新 click 属性
                        englishWord.setViewed(userdata.getViewed());
                        if (userdata.getViewed() > averageCount) {
                            // 如果浏览次数大于平均值，移除当前单词，并终止内部循环
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }

        return englishWordList;
    }

    @Override
    public void update(Userdata userdata) {
        englishMapper.update(userdata);
    }
}
