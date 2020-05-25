package com.mq.music.service;

import com.mq.music.bean.Tuijian;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;

import java.util.Map;

public interface TuiJianService {
    Page queryPage(Map paramMap);

    int deleteTuiJianBatch(Data data);

    int deleteTuiJian(Integer id);

    Page queryPageTuiJianMusic(Map paramMap);

    Tuijian getSongNameById(Integer id);

    int saveTuiJian(Tuijian tuijian);
}
