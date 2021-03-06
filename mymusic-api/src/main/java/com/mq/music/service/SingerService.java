package com.mq.music.service;

import com.mq.music.bean.Singer;
import com.mq.music.util.Page;
import com.mq.music.vo.Data;

import java.util.Map;

public interface SingerService {
    Page queryPage(Map paramMap);

    int deleteSinger(Integer id);

    int deleteBachSinger(Data data);

    int saveSinger(Singer singer);

    Singer getSingerPictureByName(String singerName);

    Singer querySingerById(Integer id);

    Singer getSingerById(Integer id);

    int updateSinger(Singer singer);
}
